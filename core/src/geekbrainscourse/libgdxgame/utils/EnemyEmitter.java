package geekbrainscourse.libgdxgame.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.math.Rnd;
import geekbrainscourse.libgdxgame.pool.EnemyPool;
import geekbrainscourse.libgdxgame.sprite.EnemyShip;

public class EnemyEmitter {

    private static final float GENERATE_INTERVAL = 4f;

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 0.3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MIDDLE_HEIGHT = 0.15f;
    private static final float ENEMY_MIDDLE_BULLET_HEIGHT = 0.02f;
    private static final int ENEMY_MIDDLE_BULLET_DAMAGE = 5;
    private static final float ENEMY_MIDDLE_RELOAD_INTERVAL = 0.4f;
    private static final int ENEMY_MIDDLE_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 0.5f;
    private static final int ENEMY_BIG_HP = 10;

    private final Vector2 enemySmallV = new Vector2(0, -0.5f);
    private final Vector2 enemySmallBulletV = new Vector2(0, -0.3f);

    private final Vector2 enemyMiddleV = new Vector2(0, -0.4f);
    private final Vector2 enemyMiddleBulletV = new Vector2(0, -0.3f);

    private final Vector2 enemyBigV = new Vector2(0, -0.3f);
    private final Vector2 enemyBigBulletV = new Vector2(0, -0.3f);

    private final Rect worldBounds;
    private final ShipResources shipResources;
    private final TextureRegion bulletRegion;

    private final TextureRegion[] enemySmallRegions;
    private final TextureRegion[] enemyMiddleRegions;
    private final TextureRegion[] enemyBigRegions;

    private final EnemyPool enemyPool;

    private float generateTimer;

    public EnemyEmitter(TextureAtlas atlas, Rect worldBounds, ShipResources sound, EnemyPool enemyPool) {
        this.bulletRegion = atlas.findRegion("bullet");
        TextureRegion enemySmallRegion = atlas.findRegion("spEnemySpaceship");
        this.enemySmallRegions = Regions.split(enemySmallRegion, 1, 2, 2);
        TextureRegion enemyMediumRegion = atlas.findRegion("spEnemySpaceship");
        this.enemyMiddleRegions = Regions.split(enemyMediumRegion, 1, 2, 2);
        TextureRegion enemyBigRegion = atlas.findRegion("spEnemySpaceship");
        this.enemyBigRegions = Regions.split(enemyBigRegion, 1, 2, 2);
        this.worldBounds = worldBounds;
        this.shipResources = sound;
        this.enemyPool = enemyPool;
    }

    public void generate(float delta, int level) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0f;
            EnemyShip enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.5f) {
                enemy.set(
                        enemySmallRegions,
                        bulletRegion,
                        shipResources,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        enemySmallBulletV,
                        ENEMY_SMALL_BULLET_DAMAGE * level,
                        ENEMY_SMALL_HP,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        enemySmallV,
                        ENEMY_SMALL_HEIGHT
                );
            } else if (type < 0.8f) {
                enemy.set(
                        enemyMiddleRegions,
                        bulletRegion,
                        shipResources,
                        ENEMY_MIDDLE_BULLET_HEIGHT,
                        enemyMiddleBulletV,
                        ENEMY_MIDDLE_BULLET_DAMAGE * level,
                        ENEMY_MIDDLE_HP,
                        ENEMY_MIDDLE_RELOAD_INTERVAL,
                        enemyMiddleV,
                        ENEMY_MIDDLE_HEIGHT
                );
            } else {
                enemy.set(
                        enemyBigRegions,
                        bulletRegion,
                        shipResources,
                        ENEMY_BIG_BULLET_HEIGHT,
                        enemyBigBulletV,
                        ENEMY_BIG_BULLET_DAMAGE * level,
                        ENEMY_BIG_HP,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        enemyBigV,
                        ENEMY_BIG_HEIGHT
                );
            }
            enemy.setPosition(Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth()), worldBounds.getTop()+enemy.getHalfHeight());
        }
    }
}
