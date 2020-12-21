package geekbrainscourse.libgdxgame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.math.Rnd;
import geekbrainscourse.libgdxgame.pool.BulletPool;
import geekbrainscourse.libgdxgame.sprite.Bullet;

public abstract class Ship extends MovableSprite {

    protected Rect worldBounds;

    protected final BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletVelocity;
    protected Sound bulletSound;
    protected float bulletHeight;
    protected int damage;
    protected int hp;

    protected float autoFireCoolDown = 0.3f;
    protected float autoFireTimer;
    protected boolean autoFire = true;

    protected float height = 0.15f;
    protected float speed = 0.5f;

    public Ship(BulletPool bulletPool) {
        this.bulletPool = bulletPool;
    }

    public Ship(float x, float y, TextureRegion region, int rows, int cols, int frames,
                BulletPool bulletPool, Sound shot) {
        super(x, y, region, rows, cols, frames);
        this.bulletPool = bulletPool;
        bulletHeight = 0.01f;
        bulletSound = shot;
        autoFireTimer = autoFireCoolDown;
    }

    @Override
    public void update(float delta) {
        autoFireTimer -= delta;
        addDestination(0, speed *delta);
        if (autoFireTimer <= 0 && autoFire) {
            shoot();
            autoFireTimer = Rnd.nextFloat(autoFireCoolDown *2, autoFireCoolDown *5);
        }
        super.update(delta);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(height);
        pos.set(worldBounds.pos);
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos.x, getTop(), bulletVelocity, bulletHeight, worldBounds, damage);
        bulletSound.setVolume(bulletSound.play(),0.2f);
    }
}
