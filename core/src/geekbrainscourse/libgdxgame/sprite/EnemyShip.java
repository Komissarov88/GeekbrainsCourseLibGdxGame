package geekbrainscourse.libgdxgame.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.base.Ship;
import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.pool.BulletPool;

public class EnemyShip extends Ship {

    private Vector2 v;

    public EnemyShip(BulletPool bulletPool, Rect worldBounds) {
        super(bulletPool);
        this.worldBounds = worldBounds;
        autoFire = false;
        speed = 0.5f;
        setAngle(180);
    }

    public EnemyShip(float x, float y, TextureAtlas atlas, BulletPool bulletPool, Sound shot) {
        super(x, y, atlas.findRegion("spEnemySpaceship"), 1, 2, 2,
                bulletPool, shot);
        bulletRegion = atlas.findRegion("bullet");
        bulletVelocity = new Vector2(0, -0.5f);
        autoFire = false;
        speed = 0.5f;
        setAngle(180);
    }

    public void set(
            TextureRegion[] regions,
            TextureRegion bulletRegion,
            Sound bulletSound,
            float bulletHeight,
            Vector2 bulletV,
            int damage,
            int hp,
            float reloadInterval,
            Vector2 v,
            float height
    ){
        this.regions = regions;
        this.bulletRegion = bulletRegion;
        this.bulletSound = bulletSound;
        this.bulletHeight = bulletHeight;
        this.bulletVelocity = bulletV;
        this.damage = damage;
        this.hp = hp;
        this.autoFireCoolDown = reloadInterval;
        this.v = v;
        setHeightProportion(height);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getBottom() < worldBounds.getTop() - getHalfHeight()) {
            speed = 0.1f;
            autoFire = true;
        }
        if (getBottom() < worldBounds.getBottom()) {
            autoFire = false;
            speed = 0.5f;
        }
    }

    @Override
    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos.x, getBottom(), bulletVelocity, bulletHeight, worldBounds, damage);
        bulletSound.setVolume(bulletSound.play(),0.2f);
    }
}
