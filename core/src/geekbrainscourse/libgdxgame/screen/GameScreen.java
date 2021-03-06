package geekbrainscourse.libgdxgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Align;

import geekbrainscourse.libgdxgame.base.BaseScreen;
import geekbrainscourse.libgdxgame.base.Button;
import geekbrainscourse.libgdxgame.base.ButtonPressed;
import geekbrainscourse.libgdxgame.base.Ship;
import geekbrainscourse.libgdxgame.math.Rnd;
import geekbrainscourse.libgdxgame.pool.BulletPool;
import geekbrainscourse.libgdxgame.pool.EnemyPool;
import geekbrainscourse.libgdxgame.pool.HealsPool;
import geekbrainscourse.libgdxgame.sprite.BackgroundSprite;
import geekbrainscourse.libgdxgame.sprite.Bullet;
import geekbrainscourse.libgdxgame.sprite.Heal;
import geekbrainscourse.libgdxgame.sprite.PlayerShip;
import geekbrainscourse.libgdxgame.sprite.Star;
import geekbrainscourse.libgdxgame.utils.EnemyEmitter;
import geekbrainscourse.libgdxgame.utils.Font;
import geekbrainscourse.libgdxgame.utils.ShipResources;

public class GameScreen extends BaseScreen {

    private BackgroundSprite background;
    private PlayerShip ship;
    TextureAtlas atlas;

    private static final int STAR_COUNT = 128;
    private Star[] stars;

    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private EnemyEmitter enemyEmitter;
    private HealsPool healsPool;

    private Music bgm;
    private ShipResources shipResources;

    private Button resetButton;
    private Button quitButton;

    private int frags;
    private Font font;
    private StringBuilder sbFrags;
    private StringBuilder sbHp;
    private StringBuilder sbLevel;


    @Override
    public void show() {
        atlas = new TextureAtlas("main.atlas");
        background = new BackgroundSprite(atlas.findRegion("bgJuno"));
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool(bulletPool, worldBounds);
        shipResources = new ShipResources(0.5f, atlas);
        ship = new PlayerShip(0, -0.5f, atlas, bulletPool, shipResources);
        enemyEmitter = new EnemyEmitter(atlas, worldBounds, shipResources, enemyPool);

        healsPool = new HealsPool(worldBounds, atlas);

        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas.findRegion("star"));
        }

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Jumpshot.mp3"));
        bgm.setLooping(true);
        bgm.setVolume(0.2f);
        bgm.play();

        resetButton = new Button(atlas.findRegion("btRestart"), 1, 2, 2,
                0, 0.1f);
        resetButton.setAction(new ButtonPressed() {
            @Override
            public void onButtonPress() {
                resetGame();
            }
        });
        quitButton = new Button(atlas.findRegion("btQuit"), 1, 2, 2,
                0, -0.1f);
        quitButton.setAction(new ButtonPressed() {
            @Override
            public void onButtonPress() {
                Gdx.app.exit();
            }
        });

        frags = 0;
        font = new Font("font.fnt", "font.png");
        font.setSize(0.02f);
        sbFrags = new StringBuilder();
        sbHp = new StringBuilder();
        sbLevel = new StringBuilder();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        updateObjects(delta);
        freeDestroyedObjects();
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        bulletPool.drawActiveObjects(batch);
        healsPool.drawActiveObjects(batch);
        enemyPool.drawActiveObjects(batch);
        if (ship.isDestroyed()) {
            resetButton.draw(batch);
            quitButton.draw(batch);
        } else {
            ship.draw(batch);
        }
        printInfo();
        batch.end();
    }

    public void freeDestroyedObjects() {
        bulletPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
        healsPool.freeAllDestroyedActiveObjects();
    }

    public void updateObjects(float delta) {
        ship.update(delta);
        for (Star star : stars) {
            star.update(delta, ship.getVelocityX(), ship.getVelocityY());
        }
        bulletPool.updateActiveObjects(delta);
        enemyPool.updateActiveObjects(delta);
        healsPool.updateActiveObjects(delta);
        checkCollision();
        if (!ship.isDestroyed()) {
            enemyEmitter.generate(delta, frags / 10 + 1);
        }
    }

    public void resetGame() {
        for (Ship s : enemyPool.getActiveObjects()) {
            s.destroy();
        }
        for (Bullet b : bulletPool.getActiveObjects()) {
            b.destroy();
        }
        for (Heal h : healsPool.getActiveObjects()) {
             h.destroy();
        }
        ship.setPosition(0, -0.5f);
        ship.flushDestroy();
        frags = 0;
    }

    public void checkCollision() {
        for (Bullet b : bulletPool.getActiveObjects()) {
            if (!ship.isOutside(b) && b.getOwner() != ship && ship.pos.dst(b.pos) <= ship.getHalfHeight()) {
                ship.hit(b.getDamage());
                b.destroy();
            }
            for (Ship s : enemyPool.getActiveObjects()) {
                if (!s.isOutside(b) && b.getOwner() == ship && s.pos.dst(b.pos) <= s.getHalfHeight()) {
                    if (s.hit(b.getDamage())) {
                        if (Rnd.nextFloat(0, 1) > 0.5f) {
                            Heal h = healsPool.obtain();
                            h.randomizeVelocity();
                            h.setPosition(s.pos.x, s.pos.y);
                        }
                        frags++;
                    }
                    b.destroy();
                }
            }
        }
        for (Heal h : healsPool.getActiveObjects()) {
            if (!ship.isOutside(h)  && ship.pos.dst(h.pos) <= ship.getHalfHeight()) {
                ship.heal(frags / 10 + 1);
                h.destroy();
                shipResources.playHeal();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        background.resize(worldBounds);
        ship.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        resetButton.resize(worldBounds);
        quitButton.resize(worldBounds);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        if (ship.isDestroyed()) {
            resetButton.touchDown(touch, pointer, button);
            quitButton.touchDown(touch, pointer, button);
        }
        ship.setDestination(touch.x, touch.y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        if (ship.isDestroyed()) {
            resetButton.touchUp(touch, pointer, button);
            quitButton.touchUp(touch, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        super.touchDragged(screenX, screenY, pointer);
        ship.setDestination(touch.x, touch.y);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        ship.keyDown(keycode);
        return super.keyDown(keycode);
    }

    public void printInfo() {
        final float MARGIN = 0.01f;
        sbFrags.setLength(0);
        font.draw(batch, sbFrags.append("FRAGS ").append(frags), worldBounds.getLeft() + MARGIN, worldBounds.getTop() - MARGIN);
        sbHp.setLength(0);
        if (ship.getHp() >= 0) {
            font.draw(batch, sbHp.append("HP ").append(ship.getHp()), worldBounds.pos.x, worldBounds.getTop() - MARGIN, Align.center);
        }
        sbLevel.setLength(0);
        font.draw(batch, sbLevel.append("LEVEL ").append(frags / 10 + 1), worldBounds.getRight() - MARGIN, worldBounds.getTop() - MARGIN, Align.right);
    }

    @Override
    public void dispose() {
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        healsPool.dispose();
        bgm.dispose();
        shipResources.dispose();
        font.dispose();
        super.dispose();
    }
}
