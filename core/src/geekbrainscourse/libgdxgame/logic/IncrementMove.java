package geekbrainscourse.libgdxgame.logic;

import com.badlogic.gdx.math.Vector2;

public class IncrementMove {

    private final Vector2 current;
    private final Vector2 destination;
    private final Vector2 move;
    private final float SPEED = 4;

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
        if (!current.equals(destination)) {
            move.set(destination);
            move.sub(current);
            move.scl(delta * SPEED);
            current.add(move);
        }
        return current;
    }
}
