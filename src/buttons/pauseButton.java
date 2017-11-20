package src.buttons;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class pauseButton implements B {
	public Button create() {
		Button button = new Button();
		Image pauseImage = new Image(getClass().getResourceAsStream("pause.png"));
		button.setGraphic(new ImageView(pauseImage));
		return button;
	}
}
