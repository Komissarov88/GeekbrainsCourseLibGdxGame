package geekbrainscourse.libgdxgame.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.utils.Regions;

public class Sprite extends Rect {

    private float angle;
    private float scale = 1;
    protected TextureRegion[] regions;
    private int frame;
    private boolean destroyed;

    protected Sprite(){}

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        regions[0] = region;
        frame = 0;
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        regions = Regions.split(region, rows, cols, frames);
        frame = 0;
    }

    public void setFrameRegion(TextureRegion region, int frame) {
        if (frame >= 0 && frame < regions.length) {
            regions[frame] = region;
        }
    }

    public void nextFrame() {
        frame++;
        if (frame >= regions.length) {
            frame = 0;
        }
    }

    public void setFrame(int frame) {
        if (frame < regions.length && frame >= 0) {
            this.frame = frame;
        }
    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    public void setWidthProportion(float width) {
        setWidth(width);
        float aspect = regions[frame].getRegionHeight() / (float) regions[frame].getRegionWidth();
        setHeight(width * aspect);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void update(float delta) {}

    public void resize(Rect bounds) {
        setHeightProportion(bounds.getHeight());
        pos.set(bounds.pos);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        destroyed = true;
    }

    public void flushDestroy() {
        destroyed = false;
    }
}
