package src.buttons;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class stopButton implements B {
	public Button create() {
		Button button = new Button();
		Image stopImage = new Image(getClass().getResourceAsStream("stop.png"));
		button.setGraphic(new ImageView(stopImage));
		return button;
	}
}
