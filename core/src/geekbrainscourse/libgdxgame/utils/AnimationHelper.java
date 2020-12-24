package geekbrainscourse.libgdxgame.utils;

public class AnimationHelper {

    private long animationStartTime;
    private int fps;
    private int animationLength;
    private int millisPerFrame;

    public AnimationHelper(int fps, int animationLength) {
        animationStartTime = System.currentTimeMillis();
        this.fps = fps;
        this.animationLength = animationLength;
        millisPerFrame = 1000 / fps;
    }

    public int getCurrentFrame() {
        long timePass = System.currentTimeMillis() - animationStartTime;
        int frame = (int) timePass / millisPerFrame;
        return frame % animationLength;
    }
}
