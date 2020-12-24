package geekbrainscourse.libgdxgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import geekbrainscourse.libgdxgame.base.BaseScreen;
import geekbrainscourse.libgdxgame.base.Button;
import geekbrainscourse.libgdxgame.base.ButtonPressed;
import geekbrainscourse.libgdxgame.base.Ship;
import geekbrainscourse.libgdxgame.pool.BulletPool;
import geekbrainscourse.libgdxgame.pool.EnemyPool;
import geekbrainscourse.libgdxgame.sprite.BackgroundSprite;
import geekbrainscourse.libgdxgame.sprite.Bullet;
import geekbrainscourse.libgdxgame.sprite.PlayerShip;
import geekbrainscourse.libgdxgame.sprite.Star;
import geekbrainscourse.libgdxgame.utils.EnemyEmitter;
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

    private Music bgm;
    private ShipResources shipResources;

    private Button resetButton;

    @Override
    public void show() {
        atlas = new TextureAtlas("main.atlas");
        background = new BackgroundSprite(atlas.findRegion("bgJuno"));
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool(bulletPool, worldBounds);
        shipResources = new ShipResources(0.5f, atlas);
        ship = new PlayerShip(0, -0.5f, atlas, bulletPool, shipResources);
        enemyEmitter = new EnemyEmitter(atlas, worldBounds, shipResources, enemyPool);

        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas.findRegion("star"));
        }

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Jumpshot.mp3"));
        bgm.setLooping(true);
        bgm.setVolume(0.2f);
        bgm.play();

        resetButton = new Button(atlas.findRegion("btRestart"), 1, 2, 2, 0.15f, 0.03f);
        resetButton.setAction(new ButtonPressed() {
            @Override
            public void onButtonPress() {
                resetGame();
            }
        });
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
        enemyPool.drawActiveObjects(batch);
        if (ship.isDestroyed()) {
            resetButton.draw(batch);
        } else {
            ship.draw(batch);
        }
        batch.end();
    }

    public void freeDestroyedObjects() {
        bulletPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
    }

    public void updateObjects(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        bulletPool.updateActiveObjects(delta);
        enemyPool.updateActiveObjects(delta);
        ship.update(delta);
        checkCollision();
        if (!ship.isDestroyed()) {
            enemyEmitter.generate(delta);
        }
    }

    public void resetGame() {
        for (Ship s : enemyPool.getActiveObjects()) {
            s.destroy();
        }
        for (Bullet b : bulletPool.getActiveObjects()) {
            b.destroy();
        }
        ship.setPosition(0, -0.5f);
        ship.flushDestroy();
    }

    public void checkCollision() {
        for (Bullet b : bulletPool.getActiveObjects()) {
            if (!ship.isOutside(b) && b.getOwner() != ship) {
                ship.hit(b.getDamage());
                b.destroy();
            }
            for (Ship s : enemyPool.getActiveObjects()) {
                if (!s.isOutside(b) && b.getOwner() == ship) {
                    s.hit(b.getDamage());
                    b.destroy();
                }
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
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        if (ship.isDestroyed()) {
            resetButton.touchDown(touch, pointer, button);
        }
        ship.setDestination(touch.x, touch.y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        if (ship.isDestroyed()) {
            resetButton.touchUp(touch, pointer, button);
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

    @Override
    public void dispose() {
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        bgm.dispose();
        shipResources.dispose();
        super.dispose();
    }
}
