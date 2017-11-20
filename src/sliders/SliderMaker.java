package src.sliders;

import javafx.scene.control.Slider;
public class SliderMaker {
	private S vSlider;
	private S fSlider;
	private S rSlider;
	
	public SliderMaker() {
		vSlider = new VolumeSlider();
		fSlider = new FrequencySlider();
		rSlider = new RateSlider();
	}
	
	public Slider createVSlider() {
		return vSlider.create();
	}
	
	public Slider createFSlider() {
		return fSlider.create();
	}
	
	public Slider createRSlider() {
		return rSlider.create();
	}
}
