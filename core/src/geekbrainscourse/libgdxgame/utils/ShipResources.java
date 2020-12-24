package geekbrainscourse.libgdxgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import geekbrainscourse.libgdxgame.base.Sprite;
import geekbrainscourse.libgdxgame.math.Rect;

public class ShipResources {

    private Sound shot;
    private Sound hit;
    private Sound explosion;
    private float volume;
    private Sprite explosionAnimation;

    public ShipResources(float volume, TextureAtlas atlas) {
        shot = Gdx.audio.newSound(Gdx.files.internal("sfx_weapon_singleshot13.wav"));
        hit = Gdx.audio.newSound(Gdx.files.internal("sfx_exp_shortest_hard6.wav"));
        explosion = Gdx.audio.newSound(Gdx.files.internal("sfx_exp_short_hard6.wav"));
        this.volume = volume;
        explosionAnimation = new Sprite(atlas.findRegion("Explosion"), 1, 12, 12);
    }

    public void playHit() {
        hit.setVolume(hit.play(),volume);
    }

    public void playShot() {
        shot.setVolume(shot.play(),volume / 2);
    }

    public void playExplosion() {
        explosion.setVolume(explosion.play(),volume*2);
    }

    public void drawExplosion(Vector2 position, int frame, SpriteBatch batch) {
        explosionAnimation.pos.set(position);
        explosionAnimation.setFrame(frame);
        explosionAnimation.draw(batch);
    }

    public void setHeightProportion(float height) {
        explosionAnimation.setHeightProportion(height);
    }

    public void dispose() {
        shot.dispose();
        hit.dispose();
        explosion.dispose();
    }
}
