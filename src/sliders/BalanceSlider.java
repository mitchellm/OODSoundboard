package sliders;

import javafx.scene.control.Slider;
import javafx.geometry.Orientation;

public class BalanceSlider implements S {

	public Slider create() {
		Slider slider = new Slider(-1, 1, 0.1);
		slider.setOrientation(Orientation.VERTICAL);
		slider.setMinHeight(450);
		slider.setMaxHeight(450);
		return slider;
	}
	
}
