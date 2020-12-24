package geekbrainscourse.libgdxgame.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.math.Rect;

public class Button extends Sprite {

    private int pointer;
    private boolean pressed;
    private ButtonPressed action;

    protected static final float HEIGHT = 0.15f;
    protected static final float MARGIN = 0.03f;
    protected float x;
    protected float y;

    public Button(TextureRegion region, int rows, int cols, int frames, float x, float y) {
        super(region, rows, cols, frames);
        this.x = x;
        this.y = y;
    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (pressed || !isMe(touch)) {
            return false;
        }
        this.pointer = pointer;
        pressed = true;
        nextFrame();
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (this.pointer != pointer || !pressed) {
            return false;
        }
        pressed = false;
        nextFrame();
        if (isMe(touch)) {
            action();
            return false;
        }
        return false;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        pos.set(x, y);
    }

    public void setAction(ButtonPressed e) {
        this.action = e;
    }

    public void action() {
        if (action != null) {
            action.onButtonPress();
        }
    }
}