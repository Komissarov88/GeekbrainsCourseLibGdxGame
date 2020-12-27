package geekbrainscourse.libgdxgame.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.pool.BulletPool;
import geekbrainscourse.libgdxgame.sprite.Bullet;
import geekbrainscourse.libgdxgame.utils.AnimationHelper;
import geekbrainscourse.libgdxgame.utils.CoolDownTimer;
import geekbrainscourse.libgdxgame.utils.ShipResources;

public abstract class Ship extends MovableSprite {

    protected Rect worldBounds;

    protected final BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletVelocity;
    protected float bulletHeight;
    protected int damage;
    protected int hp;

    protected float damageCoolDown = 0.1f;
    protected CoolDownTimer damageTimer;
    protected float autoFireCoolDown = 0.3f;
    protected boolean autoFire = true;
    protected CoolDownTimer fireTimer;

    protected float height = 0.15f;

    protected ShipResources shipResources;
    protected boolean isExploding;
    protected AnimationHelper explodingAnimation;

    public Ship(BulletPool bulletPool) {
        this.bulletPool = bulletPool;
        fireTimer = new CoolDownTimer(autoFireCoolDown);
        damageTimer = new CoolDownTimer(damageCoolDown);
        isExploding = false;
        explodingAnimation = new AnimationHelper(12, 12);
    }

    public Ship(float x, float y, TextureRegion region, int rows, int cols, int frames,
                BulletPool bulletPool, ShipResources sounds) {
        super(x, y, region, rows, cols, frames);
        this.bulletPool = bulletPool;
        bulletHeight = 0.01f;
        shipResources = sounds;
        fireTimer = new CoolDownTimer(autoFireCoolDown);
        damageTimer = new CoolDownTimer(damageCoolDown);
        isExploding = false;
        explodingAnimation = new AnimationHelper(12, 12);
    }

    public boolean hit(int damage) {
        if (damageTimer.isCool()) {
            setFrame(1);
            damageTimer.reset(damageCoolDown);
            hp -= damage;
            if (isExploding) {
                return false;
            }
            if (hp <= 0) {
                explodingAnimation.start();
                isExploding = true;
                shipResources.playExplosion();
                return true;
            }
            shipResources.playHit();
        }
        return false;
    }

    @Override
    public void update(float delta) {
        fireTimer.update(delta);
        damageTimer.update(delta);
        if (!isExploding && damageTimer.isCool()) {
            setFrame(0);
        } else {
            setFrame(1);
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
        shipResources.playShot();
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (isExploding) {
            if (explodingAnimation.isPlayedOnce()) {
                destroy();
                return;
            }
            shipResources.setHeightProportion(getHeight() * 1.5f);
            shipResources.drawExplosion(pos, explodingAnimation.getCurrentFrame(), batch);
        }
    }

    @Override
    public void flushDestroy() {
        super.flushDestroy();
        isExploding = false;
        explodingAnimation.flush();
    }
}
