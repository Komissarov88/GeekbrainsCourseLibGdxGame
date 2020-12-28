package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.base.MovableSprite;
import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.math.Rnd;

public class Heal extends MovableSprite {

    private final Rect worldBounds;
    private final Vector2 velocity;

    public Heal(float x, float y, TextureRegion region, float height, Rect worldBounds) {
        super(x, y, region);
        velocity = new Vector2(Rnd.nextFloat(-0.03f, 0.03f), Rnd.nextFloat(-0.03f, -0.06f));
        this.worldBounds = worldBounds;
        setHeightProportion(height);
    }

    public void randomizeVelocity() {
        velocity.set(Rnd.nextFloat(-0.03f, 0.03f), Rnd.nextFloat(-0.03f, -0.06f));
    }

    @Override
    public void update(float delta) {
        stop();
        setAngle(getAngle() + 50*delta);
        addDestination(velocity.x, velocity.y);
        super.update(delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }
}
