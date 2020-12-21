package geekbrainscourse.libgdxgame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.math.Rnd;
import geekbrainscourse.libgdxgame.pool.BulletPool;
import geekbrainscourse.libgdxgame.sprite.Bullet;
import geekbrainscourse.libgdxgame.utils.CoolDownTimer;

public abstract class Ship extends MovableSprite {

    protected Rect worldBounds;

    protected final BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletVelocity;
    protected Sound bulletSound;
    protected float bulletHeight;
    protected int damage;
    protected int hp;

    protected float damageCoolDown = 1.5f;
    protected CoolDownTimer damageTimer;
    protected float autoFireCoolDown = 0.3f;
    protected boolean autoFire = true;
    protected CoolDownTimer fireTimer;

    protected float height = 0.15f;
    protected float speed = 0.5f;

    public Ship(BulletPool bulletPool) {
        this.bulletPool = bulletPool;
        fireTimer = new CoolDownTimer(autoFireCoolDown);
        damageTimer = new CoolDownTimer(damageCoolDown);
    }

    public Ship(float x, float y, TextureRegion region, int rows, int cols, int frames,
                BulletPool bulletPool, Sound shot) {
        super(x, y, region, rows, cols, frames);
        this.bulletPool = bulletPool;
        bulletHeight = 0.01f;
        bulletSound = shot;
        fireTimer = new CoolDownTimer(autoFireCoolDown);
        damageTimer = new CoolDownTimer(damageCoolDown);
        hp = 100;
    }

    public void hit(int damage) {
        if (damageTimer.isCool()) {
            damageTimer.reset(damageCoolDown);
            hp -= damage;
            System.out.println(hp);
            if (hp <= 0) {
                destroy();
            }
        }
    }

    @Override
    public void update(float delta) {
        fireTimer.update(delta);
        damageTimer.update(delta);
        addDestination(0, speed *delta);
        if (fireTimer.isCool() && autoFire) {
            shoot();
            fireTimer.reset(Rnd.nextFloat(autoFireCoolDown *2, autoFireCoolDown *5));
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
