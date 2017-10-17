import javafx.scene.control.Slider;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
public class VolumeSlider implements S {
	
	public Slider create() {
		Slider slider = new Slider(0, 100, 1);
		Insets vInset = new Insets(0.0, 1200.0, 0.0, 0.0);
		slider.setOrientation(Orientation.VERTICAL);
		slider.setPadding(vInset);
		slider.setMaxHeight(450);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		return slider;
	}
	
}
