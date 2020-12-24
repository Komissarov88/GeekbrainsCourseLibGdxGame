package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.Button;
import geekbrainscourse.libgdxgame.math.Rect;

public class ButtonExit extends Button {

    public ButtonExit(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames, 0.15f, 0.03f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(height);
        setBottom(worldBounds.getBottom() + margin);
        setRight(worldBounds.getRight() - margin);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
