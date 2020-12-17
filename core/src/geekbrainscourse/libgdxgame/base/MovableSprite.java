package geekbrainscourse.libgdxgame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.Sprite;
import geekbrainscourse.libgdxgame.math.IncrementMove;

public class MovableSprite extends Sprite {

    private IncrementMove move;

    protected MovableSprite(){}

    public MovableSprite(float x, float y, TextureRegion region) {
        super(region);
        move = new IncrementMove(x, y);
    }

    public void update(float delta) {
        pos.set(move.updatePosition(delta));
    }

    public void setDestination(float x, float y) {
        move.setDestination(x, y);
    }

    public void addDestination(float x, float y) {
        move.addDestination(x, y);
    }

    public void stop() {
        move.stop();
    }
}
