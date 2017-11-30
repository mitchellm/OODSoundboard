package buttons;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class pauseButton implements B {
	public Button create() {
		Button button = new Button();
		button.setText("Pause");
		return button;
	}
}
