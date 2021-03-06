package geekbrainscourse.libgdxgame.sprite;

import geekbrainscourse.libgdxgame.base.Sprite;
import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.math.Rnd;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Star extends Sprite {

    private static final float MIN_HEIGHT = 0.005f;
    private static final float MAX_HEIGHT = 0.011f;

    private final Vector2 velocity;
    private final Vector2 parallax;
    private Rect worldBounds;

    public Star(TextureRegion region) {
        super(region);
        float vx = Rnd.nextFloat(-0.005f, 0.005f);
        float vy = Rnd.nextFloat(-0.60f, -0.045f);
        velocity = new Vector2(vx, vy);
        parallax = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(x, y);
        setHeightProportion(Rnd.nextFloat(MIN_HEIGHT, MAX_HEIGHT));
    }

    public void update(float delta, float velocityX, float velocityY) {
        this.parallax.set(-velocityX*2, -velocityY*2);
        parallax.add(velocity);
        pos.mulAdd(parallax, delta);
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        changeHeight();
    }

    private void changeHeight() {
        float height = getHeight() + 0.00001f;
        if (height > MAX_HEIGHT) {
            height = MIN_HEIGHT;
        }
        setHeightProportion(height);
    }
}
