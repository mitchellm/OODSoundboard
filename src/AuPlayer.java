import java.applet.*;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class AuPlayer implements AdvancedMediaPlayer {
	@Override
	public void playWav(String fileName) {
		//Do nothing
	}
	@Override
	public void playAu(String fileName) {
		try {
			InputStream inputStream = new FileInputStream(fileName);
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		} catch(Exception e) {
			System.out.println("Error with .au file");
			e.printStackTrace();
		}
	}
}
