import javafx.scene.control.Slider;
public class VolumeSlider implements S {
	
	public Slider create() {
		Slider slider = new Slider();
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		return slider;
	}
	
}
