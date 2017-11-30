import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class MyAudioPlayer implements MyMediaPlayer {
	MediaAdapter mediaAdapter;
	MediaPlayer mediaPlayerMain;
	String audioType = "";
	String fileName = "";
	public MyAudioPlayer(Media openClick) {
		String source = openClick.getSource();
		String type = source.substring(source.length() - 1);
		fileName = source.substring(6);

		Media mediaFile = new Media(new File(fileName).toURI().toString());
		//used to initialize toolkit
		com.sun.javafx.application.PlatformImpl.startup(()->{});
		mediaPlayerMain = new MediaPlayer(mediaFile);
		if(type.equalsIgnoreCase("3")) {
			audioType = "mp3";
		} else if(type.equalsIgnoreCase("v")) {
			audioType = "wav";
		} else if(type.equalsIgnoreCase("u")) {
			audioType = "au";
		}
	}
	@Override
	public void play() {
		if(audioType.equalsIgnoreCase("mp3")) {
			//Keeping this here in case of error not noticed
			/*Media mediaFile = new Media(new File(fileName).toURI().toString());
			//used to initialize toolkit
			com.sun.javafx.application.PlatformImpl.startup(()->{});
			MediaPlayer mediaPlayer = new MediaPlayer(mediaFile);*/
			mediaPlayerMain.play();
		} else if(audioType.equalsIgnoreCase("wav") || audioType.equalsIgnoreCase("au")){
			mediaAdapter = new MediaAdapter(audioType, fileName);
	        mediaAdapter.play();
		} else {
			System.out.println(audioType + " format not supported");
	    }
	}
	@Override
	public void pause() {
		mediaPlayerMain.pause();
	}
	@Override
	public void stop() {
		mediaPlayerMain.stop();
	}
	@Override
	public void setVolume(double value) {
		mediaPlayerMain.setVolume(value);
	}
	@Override
	public void setMute(boolean onOff) {
		mediaPlayerMain.setMute(onOff);
	}
	@Override
	public void setRate(double value) {
		mediaPlayerMain.setRate(value);
	}
	@Override
	public void setBalance(double value) {
	mediaPlayerMain.setBalance(value);
	}
}