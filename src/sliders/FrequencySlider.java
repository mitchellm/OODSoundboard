package sliders;

import javafx.scene.control.Slider;

import java.util.Observable;

import javafx.geometry.Orientation;
public class FrequencySlider implements S  {
	
	public Slider create() {
		Slider slider = new Slider(20, 20000, 100);
		slider.setOrientation(Orientation.VERTICAL);
		slider.setMinHeight(450);
		slider.setMaxHeight(450);
		return slider;
	}
	
}
