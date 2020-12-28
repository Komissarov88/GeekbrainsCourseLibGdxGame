package geekbrainscourse.libgdxgame.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import geekbrainscourse.libgdxgame.base.SpritesPool;
import geekbrainscourse.libgdxgame.math.Rect;
import geekbrainscourse.libgdxgame.sprite.Heal;

public class HealsPool extends SpritesPool<Heal> {

    private final Rect worldBounds;
    private TextureAtlas atlas;

    public HealsPool(Rect worldBounds, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.atlas = atlas;
    }

    @Override
    public Heal newObject() {
        return new Heal(0, 0, atlas.findRegion("heal"), 0.05f, worldBounds);
    }
}
