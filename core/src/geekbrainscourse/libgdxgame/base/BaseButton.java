package geekbrainscourse.libgdxgame.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseButton extends Sprite {

    private int pointer;
    private boolean pressed;

    public BaseButton(TextureRegion region) {
        super(region);
    }

    public BaseButton(TextureRegion region, int frames) {
        super(region, frames);
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

    public abstract void action();
}