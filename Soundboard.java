

import java.io.File;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import labels.LabelMaker;
import sliders.SliderMaker;
import javafx.scene.control.Button;

public class Soundboard extends Application {

	private File fileSelected;
	private MediaPlayer mp;
	Slider vSlider, fSlider;
	Label vl, fl;
	Button choose,play,stop,pause;

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) {
		SliderMaker sm = new SliderMaker();
		LabelMaker lm = new LabelMaker();

	    VBox vb = new VBox();
	    vb.setPadding(new Insets(10, 50, 50, 50));
	    vb.setSpacing(40);
	    
		vSlider = sm.createVSlider();
		vl = lm.createVLabel();
		vl.textProperty().bind(Bindings.format("%.0f", vSlider.valueProperty()));

		fSlider = sm.createFSlider();
		fl = lm.createFLabel();
		fl.textProperty().bind(Bindings.format("%.0f", fSlider.valueProperty()));

		choose = new Button("Choose sound file...");	
		play = new Button("Play");
		pause = new Button("Pause");
		stop = new Button("Stop");
		vb.getChildren().addAll(choose,play,pause,stop);
		StackPane root = new StackPane();
		root.getChildren().addAll(vl, fl, vSlider, fSlider,vb);
		primaryStage.setScene(new Scene(root, 1300, 600));
		primaryStage.setTitle("Project Test");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		vSlider.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                mp.setVolume((double)new_val/10);
            }
        }));
		
		choose.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        FileChooser fileChooser = new FileChooser();
		        fileSelected = fileChooser.showOpenDialog(primaryStage);
		        Media sound = new Media(new File(fileSelected.getAbsolutePath()).toURI().toString());
		        mp = new MediaPlayer(sound);
		        
		    }
		});
		
		play.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        try {
		            mp.play();
		            mp.setVolume(0.5);
		        } catch (NullPointerException e1) {
		            System.out.println("NPE relating to play (MediaPlayer)");
		        }
		    }
		});

		pause.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        try {
		            mp.pause();
		        } catch (NullPointerException e1) {
		            System.out.println("NPE relating to play (MediaPlayer)");
		        }
		    }
		});

		stop.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        try {
		            mp.stop();
		        } catch (NullPointerException e1) {
		            System.out.println("NPE relating to play (MediaPlayer)");
		        }
		    }
		});
	}
}
