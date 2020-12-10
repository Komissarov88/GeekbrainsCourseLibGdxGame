package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.Sprite;
import geekbrainscourse.libgdxgame.math.IncrementMove;
import geekbrainscourse.libgdxgame.math.Rect;

public class MovableSprite extends Sprite {

    private IncrementMove move;

    public MovableSprite(float x, float y, String texturePath) {
        super(texturePath);
        move = new IncrementMove(x, y);
    }

    public void drawWithPositionUpdate(SpriteBatch batch, float delta) {
        pos.set(move.updatePosition(delta));
        super.draw(batch);
    }

    public void setDestination(float x, float y) {
        move.setDestination(x, y);
    }
}
