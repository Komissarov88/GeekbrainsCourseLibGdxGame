package geekbrainscourse.libgdxgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class ShipSounds {

    private Sound shot;
    private Sound hit;
    private Sound explosion;
    private float volume;

    public ShipSounds(float volume) {
        shot = Gdx.audio.newSound(Gdx.files.internal("sfx_weapon_singleshot13.wav"));
        hit = Gdx.audio.newSound(Gdx.files.internal("sfx_exp_shortest_hard6.wav"));
        explosion = Gdx.audio.newSound(Gdx.files.internal("sfx_exp_short_hard6.wav"));
        this.volume = volume;
    }

    public void playHit() {
        hit.setVolume(hit.play(),volume);
    }

    public void playShot() {
        shot.setVolume(shot.play(),volume);
    }

    public void playExplosion() {
        explosion.setVolume(explosion.play(),volume);
    }

    public void dispose() {
        shot.dispose();
        hit.dispose();
        explosion.dispose();
    }
}
