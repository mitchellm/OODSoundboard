import javafx.scene.control.Slider;
public class SliderMaker {
	private S vSlider;
	private S fSlider;
	
	public SliderMaker() {
		vSlider = new VolumeSlider();
		fSlider = new FrequencySlider();
	}
	
	public Slider createVSlider() {
		return vSlider.create();
	}
	
	public Slider createFSlider() {
		return fSlider.create();
	}
}
