package sliders;

import javafx.scene.control.Slider;
public class SliderMaker {
	private S vSlider;
	private S rSlider;
	private S bSlider;
	
	public SliderMaker() {
		vSlider = new VolumeSlider();
		rSlider = new RateSlider();
		bSlider = new BalanceSlider();
	}
	
	public Slider createVSlider() {
		return vSlider.create();
	}
	
	public Slider createRSlider() {
		return rSlider.create();
	}
	
	public Slider createBSlider() {
		return bSlider.create();
	}
}
