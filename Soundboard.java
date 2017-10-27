
import java.io.File;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import labels.LabelMaker;
import sliders.SliderMaker;
import javafx.scene.control.Button;

public class Soundboard extends Application {

	private File fileSelected;
	private MediaPlayer mp;
	Slider sldVol, sldFreq;
	Label lblVol, lblFreq;
	Button btnPlay, btnStop, btnPause;

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) {
		//Initialize slider and label makers
		SliderMaker sliderMaker = new SliderMaker();
		LabelMaker labelMaker = new LabelMaker();
		
		/* ---START NAVBAR--- */
		//Initialize primary menu bar for navbar
		MenuBar navBar = new MenuBar();
        
        //syntax to create more items to put in navbar
        Menu navFile = new Menu("File");
        Menu navEdit = new Menu("Edit");
        Menu navOptions = new Menu("Options");
        Menu navHelp = new Menu("Help");
        
        //syntax to add options to nav tabs
        //MenuItem what appears in drop down when clicking Nav
        MenuItem about = new MenuItem("About");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        MenuItem exit = new MenuItem("Exit");
        MenuItem themes = new MenuItem("Themes");
        
        //when button is clicked this happens
        about.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	aboutInfo();
            }
        });
        open.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	mp = new MediaPlayer(openClick(mp, primaryStage));
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	saveClick();
            }
        });
        saveAs.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	saveAsClick();
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	exitClick();
            }
        });
        themes.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	themesClick();
            }
        });
        //adds the drop down option to the help tab(navHelp)
        navHelp.getItems().addAll(about);
        navFile.getItems().addAll(open, save, saveAs, exit);
        navEdit.getItems().addAll(themes);
        navOptions.getItems().addAll();
        
        //add nav tabs to navbar
        navBar.getMenus().addAll(navFile, navEdit, navOptions, navHelp);
        /* ---END NAVBAR--- */

        //Create buttons
        btnPlay = new Button("Play");
        btnPause = new Button("Pause");
		btnStop = new Button("Stop");
        
        /* ---BUTTON LISTENERS --- */
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        try {
		            mp.play();
		            mp.setVolume(0.5);
		        } catch (NullPointerException e1) {
		            System.out.println("NPE relating to play (MediaPlayer)");
		        }
		    }
		});
		btnPause.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        try {
		            mp.pause();
		        } catch (NullPointerException e1) {
		            System.out.println("NPE relating to play (MediaPlayer)");
		        }
		    }
		});
		btnStop.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        try {
		            mp.stop();
		        } catch (NullPointerException e1) {
		            System.out.println("NPE relating to play (MediaPlayer)");
		        }
		    }
		});
		/* ---END BUTTON LISTENERS--- */
		
        
        //Create HBox for holding buttons
	    HBox buttonHB = new HBox();
	    buttonHB.setPadding(new Insets(10, 50, 50, 50));
	    buttonHB.setSpacing(40);
	    buttonHB.setAlignment(Pos.CENTER);
	    buttonHB.getChildren().addAll(btnPlay,btnPause,btnStop);
	    
	    //Create sliders
		sldVol = sliderMaker.createVSlider();
		sldVol.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	try {
            		mp.setVolume((double)new_val/100.0);
	            } catch (NullPointerException e1) {
		            System.out.println("NPE relating to play (MediaPlayer)");
		        }
            }
        }));
		sldFreq = sliderMaker.createFSlider();
		sldVol.setValue(50);
		
		//Create labels
		lblVol = labelMaker.createVLabel();
		lblVol.textProperty().bind(Bindings.format("%.0f", sldVol.valueProperty()));		
		
		lblFreq = labelMaker.createFLabel();
		lblFreq.textProperty().bind(Bindings.format("%.0f", sldFreq.valueProperty()));

		//Create containers for holding sliders and labels		
		VBox vSliderVB = new VBox();
		vSliderVB.getChildren().addAll(sldVol, lblVol);
		vSliderVB.setSpacing(10);
		vSliderVB.setAlignment(Pos.CENTER);
		
		VBox fSliderVB = new VBox();
		fSliderVB.getChildren().addAll(sldFreq, lblFreq);
		fSliderVB.setSpacing(10);
		fSliderVB.setAlignment(Pos.CENTER);
		
		//Create HBox for holding slider containers
		HBox sliderHB = new HBox();
		sliderHB.getChildren().addAll(vSliderVB, fSliderVB);
		sliderHB.setPadding(new Insets(10, 50, 50, 50));
		sliderHB.setSpacing(40);
		sliderHB.setAlignment(Pos.CENTER);
		
		//Create primary borderpane for layout and add sections
		BorderPane root = new BorderPane();
		root.setTop(navBar);
		root.setCenter(sliderHB);
		root.setBottom(buttonHB);
		
		//Build stage
		primaryStage.setScene(new Scene(root, 1000, 700));
		primaryStage.setTitle("Project Test");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	private void aboutInfo() {
		
	}
	
	private Media openClick(MediaPlayer mp, Stage s) {
		FileChooser fileChooser = new FileChooser();
        fileSelected = fileChooser.showOpenDialog(s);
        Media sound = new Media(new File(fileSelected.getAbsolutePath()).toURI().toString());
        return sound;
	}
	
	private void saveClick() {
		
	}
	
	private void saveAsClick() {
		
	}
	
	private void exitClick() {
		
	}
	
	private void themesClick() {
		
	}
	
}
