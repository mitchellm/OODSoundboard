package sliders;

import javafx.scene.control.Slider;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
public class FrequencySlider implements S {
	
	public Slider create() {
		Slider slider = new Slider(20, 20000, 100);
		Insets fInset = new Insets(0.0, 1100.0, 0.0, 0.0);
		slider.setPadding(fInset);
		slider.setOrientation(Orientation.VERTICAL);
		slider.setMaxHeight(450);
		return slider;
	}
	
}
