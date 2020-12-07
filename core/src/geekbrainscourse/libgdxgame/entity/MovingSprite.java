package geekbrainscourse.libgdxgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.logic.IncrementMove;

public class MovingSprite extends IncrementMove{

    private final Texture sprite;

    public MovingSprite(String texturePath, Vector2 startPoint) {
        super(startPoint);
        sprite = new Texture(texturePath);
    }

    @Override
    public void setDestination(float x, float y) {
        super.setDestination(x - sprite.getWidth()/2, y - sprite.getHeight()/2);
    }

    public Texture getTexture() {
        return sprite;
    }

    public void dispose() {
        sprite.dispose();
    }
}
