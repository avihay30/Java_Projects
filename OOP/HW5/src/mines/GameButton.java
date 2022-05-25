package mines;

import javafx.scene.control.Button;

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
