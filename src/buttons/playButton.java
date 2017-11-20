package src.buttons;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class playButton implements B {
	public Button create() {
		Button button = new Button();
		Image playImage = new Image(getClass().getResourceAsStream("play.png"));
		button.setGraphic(new ImageView(playImage));	
		return button;
	}
}
