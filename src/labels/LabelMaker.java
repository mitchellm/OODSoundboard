package src.labels;

import javafx.scene.control.Label;
public class LabelMaker {
	private L vl;
	private L fl;
	private L rl;
	
	public LabelMaker() {
		vl = new VolumeLabel();
		fl = new FrequencyLabel();
		rl = new RateLabel();
	}
	
	public Label createVLabel() {
		return vl.create();
	}

	public Label createFLabel() {
		return fl.create();
	}
	
	public Label createRLabel() {
		return rl.create();
	}
	
}
