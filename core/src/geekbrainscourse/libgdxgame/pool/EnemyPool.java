package geekbrainscourse.libgdxgame.pool;

import geekbrainscourse.libgdxgame.base.SpritesPool;
import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public EnemyShip newObject() {
        return new EnemyShip(bulletPool, worldBounds);
    }
}
