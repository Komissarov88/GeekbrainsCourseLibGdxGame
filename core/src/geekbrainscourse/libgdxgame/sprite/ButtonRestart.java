package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.BaseButton;
import geekbrainscourse.libgdxgame.math.Rect;

public class ButtonRestart extends BaseButton {

    private static final float HEIGHT = 0.19f;

    public ButtonRestart(TextureRegion region) {
        super(region);
    }

    public ButtonRestart(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom( -getHalfHeight());
        setRight( -getHalfWidth());
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
