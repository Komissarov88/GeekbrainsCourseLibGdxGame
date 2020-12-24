package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.BaseButton;
import geekbrainscourse.libgdxgame.math.Rect;

public class Button extends BaseButton {

    private static final float HEIGHT = 0.19f;
    private static final float MARGIN = 0.03f;

    public Button(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
    }

    public void setBottomRight(float x, float y) {
        setBottom( x);
        setRight( y);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
