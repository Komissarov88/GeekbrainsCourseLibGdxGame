package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.base.Button;
import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.screen.GameScreen;

public class ButtonPlay extends Button {

    private final Game game;

    public ButtonPlay(TextureRegion region, int rows, int cols, int frames, Game game) {
        super(region, rows, cols, frames, 0.15f, 0.03f);
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(height);
        setBottom(worldBounds.getBottom() + margin);
        setLeft(worldBounds.getLeft() + margin);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }
}
