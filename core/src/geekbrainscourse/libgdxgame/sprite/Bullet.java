package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.base.MovableSprite;
import geekbrainscourse.libgdxgame.base.Sprite;
import geekbrainscourse.libgdxgame.math.Rect;

public class Bullet extends MovableSprite {

    private Rect worldBounds;
    private final Vector2 direction;
    private int damage;
    private Sprite owner;

    public Bullet() {
        regions = new TextureRegion[1];
        direction = new Vector2();
    }

    public void set(
            Sprite owner,
            TextureRegion region,
            float posX,
            float posY,
            Vector2 v0,
            float height,
            Rect worldBounds,
            int damage
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(posX, posY);
        this.direction.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(direction, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getOwner() {
        return owner;
    }
}
