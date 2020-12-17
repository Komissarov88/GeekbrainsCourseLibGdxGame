package geekbrainscourse.libgdxgame.sprite;

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
        super.draw(batch);
    }

    public void setDestination(float x, float y) {
        move.setDestination(x, y);
    }

    public void addDestination(float x, float y) {
        move.addDestination(x, y);
    }
}
