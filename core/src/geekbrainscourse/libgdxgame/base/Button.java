package geekbrainscourse.libgdxgame.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.math.Rect;

public class Button extends Sprite {

    private int pointer;
    private boolean pressed;
    private ButtonPressed action;

    protected float height;
    protected float margin;

    public Button(TextureRegion region, int rows, int cols, int frames, float height, float margin) {
        super(region, rows, cols, frames);
        this.height = height;
        this.margin = margin;
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
        setHeightProportion(height);
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