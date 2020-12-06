package geekbrainscourse.libgdxgame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.entity.MovingSprite;

public class BaseScreen implements Screen, InputProcessor {

    private SpriteBatch batch;
    private Texture background;
    private MovingSprite ship;
    private int width;
    private int height;

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("saturn.png");
        ship = new MovingSprite("spaceship.png", new Vector2(100, 100));
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, width, height);

        Vector2 shipPosition = ship.getNextPosition(delta);
        batch.draw(ship.getTexture(), shipPosition.x, shipPosition.y);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        ship.setDestination(screenX, Gdx.graphics.getHeight() - screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        ship.dispose();
    }
}
