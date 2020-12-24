package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.HeadlessException;

import geekbrainscourse.libgdxgame.base.Button;
import geekbrainscourse.libgdxgame.math.Rect;

public class ButtonExit extends Button {

    public ButtonExit(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames, 0f, 0f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        if (worldBounds.getWidth() < worldBounds.getHeight()) {
            setWidthProportion(HEIGHT);
        } else {
            setHeightProportion(HEIGHT);
        }
        setBottom(worldBounds.getBottom() + MARGIN);
        setRight(worldBounds.getRight() - MARGIN);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
