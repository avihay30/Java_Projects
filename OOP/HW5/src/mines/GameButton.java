package mines;

import javafx.scene.control.Button;

// Class that is a button and represents an in-game button
// (has x,y coordinates) of grid position.
public class GameButton extends Button {

    int x, y; // coordinates in grid

    public GameButton(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
