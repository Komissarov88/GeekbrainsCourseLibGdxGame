package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.Sprite;
import geekbrainscourse.libgdxgame.math.Rect;

public class BackgroundSprite extends Sprite{


    public BackgroundSprite(String texturePath) {
        super(texturePath);
    }

    @Override
    public void resize(Rect bounds) {
        if (bounds.getHeight() > bounds.getWidth()) {
            setHeightProportion(bounds.getHeight());
        } else {
            setWidthProportion(bounds.getWidth());
        }
        pos.set(bounds.pos);
    }
}
