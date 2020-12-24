package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.Button;
import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.screen.GameScreen;

public class ButtonPlay extends Button {

    private final Game game;

    public ButtonPlay(TextureRegion region, int rows, int cols, int frames, Game game) {
        super(region, rows, cols, frames, 0f, 0f);
        this.game = game;
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
        setLeft(worldBounds.getLeft() + MARGIN);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }
}
