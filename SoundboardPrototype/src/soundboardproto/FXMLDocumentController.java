/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundboardproto;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Mitchell M
 */
public class FXMLDocumentController implements Initializable {

    private static Stage stage;
    private FileChooser fileChooser;
    private File file;
    private MediaPlayer mp;

    @FXML
    private Button choose;

    @FXML
    private Button play;
    
    @FXML
    private Button stop;
    
    @FXML
    private Label fileSelected;

    @FXML
    private Slider volume_slider;

    @FXML
    private Slider speed_slider;

    @FXML
    private void browse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        this.file = fileChooser.showOpenDialog(this.stage);
        Media sound = new Media(new File(this.file.getAbsolutePath()).toURI().toString());
        mp = new MediaPlayer(sound);
        this.fileSelected.setText(this.file.getAbsolutePath());
    }

    @FXML
    private void handlePlay(ActionEvent event) {
        try {
            mp.play();
            mp.setVolume(0.5);
        } catch (NullPointerException e) {
            System.out.println("NPE relating to play (MediaPlayer)");
        }
    }

    @FXML
    private void handleStop(ActionEvent event) {
        try {
            mp.stop();
        } catch (NullPointerException e) {
            System.out.println("NPE relating to play (MediaPlayer)");
        }
    }

    @FXML
    private void handlePause(ActionEvent event) {
        try {
            mp.pause();
        } catch (NullPointerException e) {
            System.out.println("NPE relating to play (MediaPlayer)");
        }
    }
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        volume_slider.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                mp.setVolume((double)new_val/10);
            }
        }));
        
        speed_slider.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                mp.setRate((double) new_val);
            }
        }));
    }
    
    
    public static void setStage(Stage s) {
        FXMLDocumentController.stage = s;
    }
}
