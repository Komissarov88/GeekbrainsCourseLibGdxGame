package geekbrainscourse.libgdxgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import geekbrainscourse.libgdxgame.base.BaseScreen;
import geekbrainscourse.libgdxgame.sprite.BackgroundSprite;
import geekbrainscourse.libgdxgame.sprite.MovableSprite;

public class GameScreen extends BaseScreen {
    private BackgroundSprite background;
    private MovableSprite ship;
    TextureAtlas atlas;

    @Override
    public void show() {
        atlas = new TextureAtlas("menu.atlas");
        background = new BackgroundSprite(atlas.findRegion("bgJuno"));
        ship = new MovableSprite(0.3f, 0.3f, atlas.findRegion("spSpaceship"));
        ship.setScale(0.3f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        ship.drawWithPositionUpdate(batch, delta);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        background.resize(worldBounds);
        ship.resize(worldBounds);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        ship.setDestination(touch.x, touch.y);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        super.touchDragged(screenX, screenY, pointer);
        ship.setDestination(touch.x, touch.y);
        return false;
    }

    @Override
    public void dispose() {
        atlas.dispose();
        super.dispose();
    }
}
