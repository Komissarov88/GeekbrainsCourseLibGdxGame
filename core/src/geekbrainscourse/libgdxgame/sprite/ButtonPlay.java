package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.BaseButton;
import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.screen.GameScreen;

public class ButtonPlay extends BaseButton {

    private static final float HEIGHT = 0.19f;
    private static final float MARGIN = 0.03f;

    private final Game game;

    public ButtonPlay(TextureRegion region, Game game) {
        super(region);
        this.game = game;
    }

    public ButtonPlay(TextureRegion region, int rows, int cols, int frames, Game game) {
        super(region, rows, cols, frames);
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + MARGIN);
        setLeft(worldBounds.getLeft() + MARGIN);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }
}
