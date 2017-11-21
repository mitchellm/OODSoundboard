package sliders;

import javafx.scene.control.Slider;
import javafx.geometry.Orientation;
public class RateSlider implements S {
	
	public Slider create() {
		Slider slider = new Slider(0.1, 8, 1);
		slider.setOrientation(Orientation.VERTICAL);
		slider.setMinHeight(450);
		slider.setMaxHeight(450);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		return slider;
	}
	
}
