import javafx.scene.control.Slider;
public class SliderMaker {
	private S vSlider;
	
	public SliderMaker() {
		vSlider = new VolumeSlider();
	}
	
	public Slider createSlider() {
		return vSlider.create();
	}
}
