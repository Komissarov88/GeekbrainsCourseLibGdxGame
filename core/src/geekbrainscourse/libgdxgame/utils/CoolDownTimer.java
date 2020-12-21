package geekbrainscourse.libgdxgame.utils;

public class CoolDownTimer {

    private boolean isCool;
    private float coolDown;
    private float timer;

    public CoolDownTimer(float coolDown) {
        this.coolDown = coolDown;
        isCool = false;
        timer = this.coolDown;
    }

    public boolean isCool() {
        return isCool;
    }

    public void update(float delta) {
        timer -= delta;
        if (timer <= 0) {
            isCool = true;
        }
    }

    public void reset(float coolDown) {
        isCool = false;
        this.coolDown = coolDown;
        timer = coolDown;
    }
}
