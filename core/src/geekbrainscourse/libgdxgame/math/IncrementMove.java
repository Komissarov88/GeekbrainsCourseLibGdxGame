package geekbrainscourse.libgdxgame.math;

import com.badlogic.gdx.math.Vector2;

public class IncrementMove {

    protected final Vector2 current;
    private final Vector2 destination;
    private final Vector2 move;
    private final float SPEED = 4;
    private final float MAX_LEN = 0.0005f;

    public IncrementMove(float x, float y) {
        current = new Vector2(x, y);
        destination = new Vector2(x, y);
        move = new Vector2(0, 0);
    }

    public void setDestination(float x, float y) {
        destination.set(x, y);
    }

    public void addDestination(float x, float y) {
        destination.add(x, y);
    }

    public void setCurrent(float x, float y) {
        current.set(x, y);
    }

    public void stop() {
        destination.set(current);
    }

    public Vector2 updatePosition(float delta) {
        if (current.equals(destination)) {
            return current;
        }
        move.set(destination);
        move.sub(current);
        if (move.len() > MAX_LEN) {
            move.scl(delta * SPEED);
            move.clamp(0, 0.01f);
            current.add(move);
        } else {
            current.set(destination);
        }
        return current;
    }
}
