package geekbrainscourse.libgdxgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import geekbrainscourse.libgdxgame.base.BaseScreen;
import geekbrainscourse.libgdxgame.base.Ship;
import geekbrainscourse.libgdxgame.pool.BulletPool;
import geekbrainscourse.libgdxgame.pool.EnemyPool;
import geekbrainscourse.libgdxgame.sprite.BackgroundSprite;
import geekbrainscourse.libgdxgame.sprite.Bullet;
import geekbrainscourse.libgdxgame.sprite.EnemyShip;
import geekbrainscourse.libgdxgame.sprite.PlayerShip;
import geekbrainscourse.libgdxgame.sprite.Star;
import geekbrainscourse.libgdxgame.utils.EnemyEmitter;

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
    private Sound shot;

    @Override
    public void show() {
        atlas = new TextureAtlas("main.atlas");
        background = new BackgroundSprite(atlas.findRegion("bgJuno"));
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool(bulletPool, worldBounds);
        shot = Gdx.audio.newSound(Gdx.files.internal("sfx_weapon_singleshot13.wav"));
        ship = new PlayerShip(0, -0.5f, atlas, bulletPool, shot);
        enemyEmitter = new EnemyEmitter(atlas, worldBounds, shot, enemyPool);

        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas.findRegion("star"));
        }

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Jumpshot.mp3"));
        bgm.setLooping(true);
        bgm.setVolume(0.2f);
        bgm.play();
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
        ship.draw(batch);
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
        enemyEmitter.generate(delta);
    }

    public void checkCollision() {
        for (Bullet b : bulletPool.getActiveObjects()) {
            if (!ship.isOutside(b) && b.getOwner() != ship) {
                ship.hit(b.getDamage());
            }
            for (Ship s : enemyPool.getActiveObjects()) {
                if (!s.isOutside(b) && b.getOwner() == ship) {
                    s.hit(b.getDamage());
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
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        ship.setDestination(touch.x, touch.y);
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
        shot.dispose();
        enemyPool.dispose();
        bgm.dispose();
        super.dispose();
    }
}
