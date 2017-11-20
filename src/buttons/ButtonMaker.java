package src.buttons;
import javafx.scene.control.Button;

public class ButtonMaker {
	private B play;
	private B stop;
	private B pause;
	
	public ButtonMaker() {
		play = new playButton();
		stop = new stopButton();
		pause = new pauseButton();
	}
	
	public Button createPlayButton() {
		return play.create();
	}
	
	public Button createStopButton() {
		return stop.create();
	}
	
	public Button createPauseButton() {
		return pause.create();
	}
	

}
