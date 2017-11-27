import  sun.audio.*;
import  java.io.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class WavPlayer implements AdvancedMediaPlayer, LineListener {
	boolean playCompleted;
	@Override
	public void playWav(String fileName) {
		try {
			AudioInputStream audioInSt = AudioSystem.getAudioInputStream(new File(fileName));
			AudioFormat format = audioInSt.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.addLineListener(this);
			clip.open(audioInSt);
			clip.start();
			while (!playCompleted) {
                // wait for the play to complete
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
			clip.close();
		} catch (Exception e) {
			System.out.println("Error playing .wav file");
			e.printStackTrace();
		}
	}
	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP) {
            playCompleted = true;
        }
	}
	@Override
	public void playAu(String fileName) {
		//Do nothing
	}
}
