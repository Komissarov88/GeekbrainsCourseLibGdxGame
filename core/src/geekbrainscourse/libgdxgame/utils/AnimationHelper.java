package geekbrainscourse.libgdxgame.utils;

public class AnimationHelper {

    private long animationStartTime;
    private int fps;
    private int animationLength;
    private int millisPerFrame;
    private boolean isStarted;

    public AnimationHelper(int fps, int animationLength) {
        animationStartTime = System.currentTimeMillis();
        this.fps = fps;
        this.animationLength = animationLength;
        millisPerFrame = 1000 / fps;
        isStarted = false;
    }

    public void start() {
        animationStartTime = System.currentTimeMillis();
        isStarted = true;
    }

    public int getCurrentFrame() {
        long timePass = System.currentTimeMillis() - animationStartTime;
        int frame = (int) timePass / millisPerFrame;
        return frame % animationLength;
    }

    public boolean isPlayedOnce() {
        long timePass = System.currentTimeMillis() - animationStartTime;
        int frame = (int) timePass / millisPerFrame;
        return isStarted && frame >= animationLength;
    }

    public void flush() {
        isStarted = false;
    }
}
