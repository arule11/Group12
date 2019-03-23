
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    public int x, y;
    public Ship ship = null;
    public boolean wasShot = false;

    private Board board;

    public Cell(int x, int y, Board board) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.LIGHTBLUE);
        setStroke(Color.DARKBLUE);
    }

    public boolean shoot() {
        wasShot = true;
        setFill(Color.FUCHSIA);
        setStroke(Color.FUCHSIA);

        if (ship != null) {
            ship.hit();
            setFill(Color.ORANGE);
            setStroke(Color.ORANGE);
            if (!ship.isAlive()) {
                board.ships--;
            }
            return true;
        }

        return false;
    }
}

