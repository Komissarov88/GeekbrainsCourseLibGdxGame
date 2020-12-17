package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.BaseButton;
import geekbrainscourse.libgdxgame.math.Rect;

public class ButtonExit extends BaseButton {

    private static final float HEIGHT = 0.19f;
    private static final float MARGIN = 0.03f;

    public ButtonExit(TextureRegion region) {
        super(region);
    }

    public ButtonExit(TextureRegion region, int frames) {
        super(region, frames);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + MARGIN);
        setRight(worldBounds.getRight() - MARGIN);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
