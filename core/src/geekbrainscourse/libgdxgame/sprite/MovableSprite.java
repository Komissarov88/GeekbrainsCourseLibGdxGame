package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.Sprite;
import geekbrainscourse.libgdxgame.math.IncrementMove;

public class MovableSprite extends Sprite {

    private final IncrementMove move;

    public MovableSprite(float x, float y, TextureRegion region) {
        super(region);
        move = new IncrementMove(x, y);
    }

    public void drawWithPositionUpdate(SpriteBatch batch, float delta) {
        pos.set(move.updatePosition(delta));
        keyboardControls(delta);
        super.draw(batch);
    }

    public void setDestination(float x, float y) {
        move.setDestination(x, y);
    }

    public void addDestination(float x, float y) {
        move.addDestination(x, y);
    }

    public void keyboardControls(float delta) {
        final float SPEED = 0.5f;
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
}
