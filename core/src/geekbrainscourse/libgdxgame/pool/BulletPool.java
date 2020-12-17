package geekbrainscourse.libgdxgame.pool;

import geekbrainscourse.libgdxgame.base.SpritesPool;
import geekbrainscourse.libgdxgame.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    public Bullet newObject() {
        return new Bullet();
    }
}
