package mediaplayer;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author Mitchell M
 */
public class SoundExample extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        String musicFile = "music.mp3";
        primaryStage.setTitle("Playing " + musicFile);
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();
        mediaPlayer.setVolume(0.5);
        
        Timer event = new Timer();
        event.schedule(new TimerTask() {
            @Override
            public void run() {
                if (Math.random() * 2 > 1) {
                    double n = mediaPlayer.getVolume() - 0.2;
                    System.out.println("Volume set to: " + n);
                    mediaPlayer.setVolume(n);
                } else {
                    double n = mediaPlayer.getVolume() + 0.2;
                    System.out.println("Volume set to: " + n);
                    mediaPlayer.setVolume(n);
                }
                
                if (0 >= mediaPlayer.getVolume() || mediaPlayer.getVolume() > 1.2) {
                    mediaPlayer.setVolume(0.5);
                }
            }
        }, 0, 1000 * 1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
