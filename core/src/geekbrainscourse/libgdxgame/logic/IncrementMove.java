package geekbrainscourse.libgdxgame.logic;

import com.badlogic.gdx.math.Vector2;

public class IncrementMove {

    private final Vector2 current;
    private final Vector2 destination;
    private final Vector2 move;
    private final float SPEED = 4;
    private final float MAX_LEN = 0.5f;

    public IncrementMove(Vector2 startPoint) throws NullPointerException {
        if (startPoint == null) {
            startPoint = new Vector2(0, 0);
        }
        current = startPoint.cpy();
        destination = startPoint.cpy();
        move = new Vector2(0, 0);
    }

    public void setDestination(float x, float y) {
        destination.set(x, y);
    }

    public Vector2 getNextPosition(float delta) {
        if (current.equals(destination)) {
            return current;
        }
        move.set(destination);
        move.sub(current);
        if (move.len() > MAX_LEN) {
            move.scl(delta * SPEED);
            current.add(move);
        } else {
            current.set(destination);
        }
        return current;
    }
}
