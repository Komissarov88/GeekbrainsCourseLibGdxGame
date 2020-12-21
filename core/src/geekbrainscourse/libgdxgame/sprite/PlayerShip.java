package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.base.Ship;
import geekbrainscourse.libgdxgame.pool.BulletPool;

public class PlayerShip extends Ship {

    public PlayerShip(float x, float y, TextureAtlas atlas, BulletPool bulletPool, Sound shot) {
        super(x, y, atlas.findRegion("spSpaceship"), 1, 2, 2,
                bulletPool, shot);
        bulletRegion = atlas.findRegion("bullet");
        bulletVelocity = new Vector2(0, 0.5f);
        autoFire = false;
        damage = 100;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        checkStop(delta);
        keyboardControls(delta);
    }

    public void checkStop(float delta) {
        boolean isOutsideRight = getRight() > worldBounds.getRight();
        boolean isOutsideLeft = getLeft() < worldBounds.getLeft();
        boolean isOutsideTop = getTop() > worldBounds.getTop();
        boolean isOutsideBottom = getBottom() < worldBounds.getBottom();

        if (isOutsideLeft) {
            addDestination(speed *delta, 0);
        }
        if (isOutsideRight) {
            addDestination(-speed *delta, 0);
        }
        if (isOutsideTop) {
            addDestination(0, -speed *delta);
        }
        if (isOutsideBottom) {
            addDestination(0, speed *delta);
        }
    }

    public void keyboardControls(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            addDestination(-speed *delta, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            addDestination(speed *delta, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            addDestination(0, speed *delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            addDestination(0, -speed *delta);
        }

        if (fireTimer.isCool() && (Gdx.input.isKeyPressed(Input.Keys.SPACE) || autoFire)) {
            shoot();
            fireTimer.reset(autoFireCoolDown);
        }
    }

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.B) {
            autoFire = !autoFire;
        }
        return false;
    }
}
