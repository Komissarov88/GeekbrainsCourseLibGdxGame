package geekbrainscourse.libgdxgame.logic;

import com.badlogic.gdx.math.Vector2;

public class IncrementMove {

    private final Vector2 current;
    private final Vector2 destination;
    private final float SPEED = 6;

    public IncrementMove(Vector2 startPoint) {
        current = startPoint.cpy();
        destination = startPoint.cpy();
    }

    public void setDestination(float x, float y) {
        destination.set(x, y);
    }

    public Vector2 getNextPosition(float delta) {
        if (!current.equals(destination)) {
            Vector2 move = new Vector2(destination);
            move.sub(current);
            move.scl(delta * SPEED);
            current.add(move);
        }
        return current;
    }
}
