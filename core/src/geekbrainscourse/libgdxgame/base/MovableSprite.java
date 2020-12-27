package geekbrainscourse.libgdxgame.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.math.IncrementMove;

public class MovableSprite extends Sprite {

    protected IncrementMove move;

    protected MovableSprite(){
        move = new IncrementMove(0, 0);
    }

    public MovableSprite(float x, float y, TextureRegion region) {
        super(region);
        move = new IncrementMove(x, y);
    }

    public MovableSprite(float x, float y, TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
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

    public float getVelocityX() {
        return move.getVelocityX();
    }

    public float getVelocityY() {
        return move.getVelocityY();
    }

    public void setPosition(float x, float y) {
        move.setCurrent(x, y);
        move.stop();
        pos.x = x;
        pos.y = y;
    }

    public void stop() {
        move.stop();
    }
}
