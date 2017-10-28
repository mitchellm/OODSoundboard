
import java.io.File;

import buttons.ButtonMaker;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import labels.LabelMaker;
import menu_items.MenuItemsMaker;
import sliders.SliderMaker;
import javafx.scene.control.Button;

public class Soundboard extends Application {

	private File fileSelected;
	private MediaPlayer mp;
	Slider sldVol, sldFreq;
	Label lblVol, lblFreq;
	Button btnPlay, btnStop, btnPause;
	MenuItem about, exit, open, save, saveAs, themes;
	boolean isMuted = false;
	ToggleButton btnMute;

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) {
		//Initialize facade pattern makers
		SliderMaker sldMaker = new SliderMaker();
		LabelMaker lblMaker = new LabelMaker();
		ButtonMaker btnMaker = new ButtonMaker();
		MenuItemsMaker miMaker = new MenuItemsMaker();
		
		/* ---START NAVBAR--- */
		//Create navigation bar and its contents
		MenuBar navBar = new MenuBar();
        Menu navFile = new Menu("File");
        Menu navEdit = new Menu("Edit");
        Menu navOptions = new Menu("Options");
        Menu navHelp = new Menu("Help");
        
        //MenuItem what appears in drop down when clicking Nav
        about = miMaker.createAbout();
        open = miMaker.createOpen();
        save = miMaker.createSave();
        saveAs = miMaker.createSaveAs();
        exit = miMaker.createExit();
        themes = miMaker.createThemes();
        
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

        /* ---START BUTTONS--- */
        btnPlay = btnMaker.createPlayButton();
        btnPause = btnMaker.createPauseButton();
		btnStop = btnMaker.createStopButton();
		btnMute = new ToggleButton();
		Image muteImage = new Image(getClass().getResourceAsStream("mute.png"));
		btnMute.setGraphic(new ImageView(muteImage));
		
        
        //Button listeners
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
		btnMute.selectedProperty().addListener(new ChangeListener<Boolean>() {			
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {
		            if (newValue) {
		            	mp.setMute(true);
		            	isMuted = true;
		            }
		            if (!newValue) {
		            	mp.setMute(false);
		            	isMuted = false;
		        	}
		        } catch (NullPointerException e1) {
		            System.out.println("NPE relating to mute (MediaPlayer)");
		        }
			}
		});
		        
		
		//Create HBox for holding buttons
	    HBox buttonHB = new HBox();
	    //buttonHB.setPadding(new Insets(10, 50, 50, 50));
	    //buttonHB.setSpacing(40);
	    buttonHB.setAlignment(Pos.CENTER);
	    buttonHB.getChildren().addAll(btnPlay,btnPause,btnStop,btnMute);
		/* ---END BUTTONS--- */
		
        
	    /* ---START SLIDERS--- */
	    double sliderWidth = 450;
	    double volInitial = 50;
		sldVol = sldMaker.createVSlider();
		sldVol.setValue(volInitial);
		ProgressBar prgVol = new ProgressBar(volInitial/sldVol.getMax());
		prgVol.setMinWidth(sliderWidth);
		prgVol.setMaxWidth(sliderWidth);
		prgVol.getTransforms().addAll(new Rotate(-90, 0, 0));
		prgVol.setStyle("-fx-accent:blue");
		prgVol.setPadding(new Insets(0, 7, 30, 7));
		Group grpVol = new Group();
		grpVol.getChildren().add(prgVol);
		StackPane spVol = new StackPane();
		spVol.getChildren().addAll(grpVol, sldVol);
		sldVol.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	prgVol.setProgress(new_val.doubleValue()/100);
            	try {
            		mp.setVolume((double)new_val/100.0);
	            } catch (NullPointerException e1) {
		            System.out.println("NPE relating to volume slider (MediaPlayer)");
		        }
            }
        }));
		sldFreq = sldMaker.createFSlider();
		ProgressBar prgFreq = new ProgressBar(0);
		prgFreq.setMinWidth(sliderWidth);
		prgFreq.setMaxWidth(sliderWidth);
		prgFreq.getTransforms().addAll(new Rotate(-90, 0, 0));
		prgFreq.setPadding(new Insets(0, 7, 0, 7));
		prgFreq.setStyle("-fx-accent:blue");
		Group grpFreq = new Group();
		grpFreq.getChildren().add(prgFreq);
		StackPane spFreq = new StackPane();
		spFreq.getChildren().addAll(grpFreq, sldFreq);
		sldFreq.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	prgFreq.setProgress(new_val.doubleValue()/19980);
            }
        }));
		
		//Create labels
		lblVol = lblMaker.createVLabel();
		lblVol.textProperty().bind(Bindings.format("%.0f", sldVol.valueProperty()));	
		lblVol.setPadding(new Insets(0, 30, 0, 0));
		
		lblFreq = lblMaker.createFLabel();
		lblFreq.textProperty().bind(Bindings.format("%.0f", sldFreq.valueProperty()));

		//Create containers for holding sliders and labels		
		double vbWidth = 65;
		
		VBox vSliderVB = new VBox();
		vSliderVB.getChildren().addAll(spVol, lblVol);
		vSliderVB.setSpacing(10);
		vSliderVB.setAlignment(Pos.CENTER);
		vSliderVB.setMinWidth(vbWidth);
		vSliderVB.setMaxWidth(vbWidth);
		
		VBox fSliderVB = new VBox();
		fSliderVB.getChildren().addAll(spFreq, lblFreq);
		fSliderVB.setSpacing(10);
		fSliderVB.setAlignment(Pos.CENTER);
		fSliderVB.setMinWidth(vbWidth);
		fSliderVB.setMaxWidth(vbWidth);
		
		//Create HBox for holding slider containers
		HBox sliderHB = new HBox();
		sliderHB.getChildren().addAll(vSliderVB, fSliderVB);
		sliderHB.setPadding(new Insets(10, 50, 50, 50));
		sliderHB.setSpacing(40);
		sliderHB.setAlignment(Pos.CENTER);
		/* ---END SLIDERS--- */
		
		//Create primary borderpane for layout and add sections
		BorderPane root = new BorderPane();
		root.setTop(navBar);
		root.setCenter(sliderHB);
		root.setBottom(buttonHB);
		root.setStyle("-fx-background-color: transparent;");
		
		//Build stage
		Scene primaryScene = new Scene(root, 1000, 700, Color.web("#2c2f33"));
		primaryScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(primaryScene);
		primaryStage.setTitle("Soundboard");
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
