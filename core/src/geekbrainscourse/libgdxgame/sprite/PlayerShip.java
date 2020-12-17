package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.base.MovableSprite;
import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.pool.BulletPool;

public class PlayerShip extends MovableSprite {

    private Rect worldBounds;
    private final float HEIGHT = 0.15f;
    private final float SPEED = 0.5f;

    private final BulletPool bulletPool;
    private final TextureRegion bulletRegion;
    private final Vector2 bulletV;

    private final float AUTO_FIRE_COOL_DOWN = 0.3f;
    private float autoFireTimer;
    private boolean autoFire = false;

    public PlayerShip(float x, float y, TextureAtlas atlas, BulletPool bulletPool) {
        super(x, y, atlas.findRegion("spSpaceship"));
        this.bulletPool = bulletPool;
        bulletRegion = atlas.findRegion("bullet");
        bulletV = new Vector2(0, 0.5f);
        autoFireTimer = AUTO_FIRE_COOL_DOWN;
    }

    @Override
    public void update(float delta) {
        autoFireTimer -= delta;
        checkStop(delta);
        keyboardControls(delta);
        super.update(delta);
    }

    public void checkStop(float delta) {
        boolean isOutsideRight = getRight() > worldBounds.getRight();
        boolean isOutsideLeft = getLeft() < worldBounds.getLeft();
        boolean isOutsideTop = getTop() > worldBounds.getTop();
        boolean isOutsideBottom = getBottom() < worldBounds.getBottom();

        if (isOutsideLeft) {
            stop();
            addDestination(SPEED*delta, 0);
        }
        if (isOutsideRight) {
            stop();
            addDestination(-SPEED*delta, 0);
        }
        if (isOutsideTop) {
            stop();
            addDestination(0, -SPEED*delta);
        }
        if (isOutsideBottom) {
            stop();
            addDestination(0, SPEED*delta);
        }
    }

    public void keyboardControls(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            addDestination(-SPEED*delta, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            addDestination(SPEED*delta, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            addDestination(0, SPEED*delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            addDestination(0, -SPEED*delta);
        }

        if (autoFireTimer <= 0 && (Gdx.input.isKeyPressed(Input.Keys.SPACE) || autoFire)) {
            shoot();
            autoFireTimer = AUTO_FIRE_COOL_DOWN;
        }
    }

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.B) {
            autoFire = !autoFire;
        }
        return false;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        pos.set(worldBounds.pos);
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos.x, getTop(), bulletV, 0.01f, worldBounds, 1);
    }
}
