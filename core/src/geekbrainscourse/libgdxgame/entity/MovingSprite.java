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

    public Texture getTexture() {
        return sprite;
    }

    public int getWidth() {
        return sprite.getWidth();
    }

    public int getHeight() {
        return sprite.getHeight();
    }

    public void dispose() {
        sprite.dispose();
    }
}
