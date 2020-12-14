package geekbrainscourse.libgdxgame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import geekbrainscourse.libgdxgame.base.BaseScreen;
import geekbrainscourse.libgdxgame.sprite.BackgroundSprite;
import geekbrainscourse.libgdxgame.sprite.ButtonExit;
import geekbrainscourse.libgdxgame.sprite.ButtonPlay;

public class MenuScreen extends BaseScreen {

    private ButtonExit exitButton;
    private ButtonPlay playButton;
    private BackgroundSprite background;
    TextureAtlas atlas;

    private final Game game;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("menu.atlas");
        background = new BackgroundSprite(atlas.findRegion("bgJuno"));

        exitButton = new ButtonExit(atlas.findRegion("btQuit"), 2);
        exitButton.setFrameRegion(atlas.findRegion("btQuitPressed"), 1);

        playButton = new ButtonPlay(atlas.findRegion("btPlay"), 2, game);
        playButton.setFrameRegion(atlas.findRegion("btPlayPressed"), 1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        exitButton.draw(batch);
        playButton.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        background.resize(worldBounds);
        exitButton.resize(worldBounds);
        playButton.resize(worldBounds);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        exitButton.touchDown(touch, pointer, button);
        playButton.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        exitButton.touchUp(touch, pointer, button);
        playButton.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        super.touchDragged(screenX, screenY, pointer);
        return false;
    }

    @Override
    public void dispose() {
        atlas.dispose();
        super.dispose();
    }
}
