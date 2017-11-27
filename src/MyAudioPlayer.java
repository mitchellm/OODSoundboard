import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyAudioPlayer implements MyMediaPlayer {
	MediaAdapter mediaAdapter;

	@Override
	public void play(String audioType, String fileName) {
		if(audioType.equalsIgnoreCase("mp3")) {
			Media mediaFile = new Media(new File(fileName).toURI().toString());
			//used to initialize toolkit
			com.sun.javafx.application.PlatformImpl.startup(()->{});
			MediaPlayer mediaPlayer = new MediaPlayer(mediaFile);
			mediaPlayer.play();
		} else if(audioType.equalsIgnoreCase("wav") || audioType.equalsIgnoreCase("au")){
			mediaAdapter = new MediaAdapter(audioType);
	        mediaAdapter.play(audioType, fileName);
		} else {
			System.out.println(audioType + " format not supported");
	    }
	}
}
