package labels;

import javafx.scene.control.Label;
public class LabelMaker {
	private L vl;
	private L rl;
	private L bl;
	
	public LabelMaker() {
		vl = new VolumeLabel();
		rl = new RateLabel();
		bl = new BalanceLabel();
	}
	
	public Label createVLabel() {
		return vl.create();
	}
	
	public Label createRLabel() {
		return rl.create();
	}
	
	public Label createBLabel() {
		return bl.create();
	}
}
