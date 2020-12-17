package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.MovableSprite;
import geekbrainscourse.libgdxgame.math.Rect;

public class PlayerShip extends MovableSprite {

    private Rect worldBounds;
    private final float HEIGHT = 0.15f;
    private final float SPEED = 0.5f;

    public PlayerShip(float x, float y, TextureRegion region) {
        super(x, y, region);
    }

    @Override
    public void update(float delta) {
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
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        pos.set(worldBounds.pos);
    }
}
