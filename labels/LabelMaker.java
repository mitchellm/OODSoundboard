package labels;

import javafx.scene.control.Label;
public class LabelMaker {
	private L vl;
	private L fl;
	
	public LabelMaker() {
		vl = new VolumeLabel();
		fl = new FrequencyLabel();
	}
	
	public Label createVLabel() {
		return vl.create();
	}
	
	public Label createFLabel() {
		return fl.create();
	}
	
}
