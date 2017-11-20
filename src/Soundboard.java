package src;

import java.io.File;
import java.util.Optional;

import src.buttons.ButtonMaker;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import src.labels.LabelMaker;
import src.menu_items.MenuItemsMaker;
import src.sliders.SliderMaker;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;

public class Soundboard extends Application {

	private File fileSelected;
	private MediaPlayer mp;
	
	Slider sldVol, sldFreq, sldRate;
	Label lblVol, lblFreq, lblRate;
	ProgressBar prgVol, prgFreq, prgRate;
	
	Button btnPlay, btnStop, btnPause;
	MenuItem mniAbout, mniExit, mniOpen, mniSave, mniSaveAs;
	CheckMenuItem cmiThemeDef, cmiTheme1, cmiTheme2, cmiTheme3;
	boolean isMuted = false;
	ToggleButton btnMute;
	Scene primaryScene;

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) {
		//Choose default label color
		String strLblColor = "white";
		String strLblColorCSS = "-fx-text-fill: "+strLblColor+";";
		
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
        Menu navThemes = new Menu("Themes");
        
        //MenuItem what appears in drop down when clicking Nav
        mniAbout = miMaker.createAbout();
        mniOpen = miMaker.createOpen();
        mniSave = miMaker.createSave();
        mniSaveAs = miMaker.createSaveAs();
        mniExit = miMaker.createExit();
        cmiThemeDef = new CheckMenuItem("Default");
        cmiThemeDef.selectedProperty().set(true);
        cmiTheme1 = new CheckMenuItem("Theme 1");
        cmiTheme2 = new CheckMenuItem("Theme 2");
        cmiTheme3 = new CheckMenuItem("Theme 3");
        
        //when button is clicked this happens
        mniAbout.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	aboutInfo();
            }
        });
        mniOpen.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	try {
            		mp = new MediaPlayer(openClick(mp, primaryStage));
	            	btnPlay.setDisable(false);
	        		btnPause.setDisable(false);
	        		btnStop.setDisable(false);
	        		btnMute.setDisable(false);
	        		
	        		sldVol.setDisable(false);
	        		prgVol.setDisable(false);

	        		prgFreq.setDisable(false);
	        		sldFreq.setDisable(false);
	        		
	        		prgRate.setDisable(false);
	        		sldRate.setDisable(false);
            	} catch(NullPointerException e) {
            		System.out.println("No file selected.");
            	}
            }
        });
        mniSave.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	saveClick();
            }
        });
        mniSaveAs.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	saveAsClick();
            }
        });
        mniExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	exitClick();
            }
        });
        cmiThemeDef.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	themeDefClick();
            }
        });
        cmiTheme1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	theme1Click();
            }
        });
        cmiTheme2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	theme2Click();
            }
        });
        cmiTheme3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	theme3Click();
            }
        });
        //adds the drop down option to the help tab(navHelp)
        navHelp.getItems().addAll(mniAbout);
        navFile.getItems().addAll(mniOpen, mniSave, mniSaveAs, mniExit);
        navEdit.getItems().addAll(navThemes);
        navThemes.getItems().addAll(cmiThemeDef, cmiTheme1, cmiTheme2, cmiTheme3);
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
		
		//Disable buttons before file is loaded
		btnPlay.setDisable(true);
		btnPause.setDisable(true);
		btnStop.setDisable(true);
		btnMute.setDisable(true);
		
        
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

	    //VOLUME
	    double volInitial = 50;
		sldVol = sldMaker.createVSlider();
		sldVol.setValue(volInitial);
		prgVol = new ProgressBar(volInitial/sldVol.getMax());
		prgVol.setMinWidth(sliderWidth);
		prgVol.setMaxWidth(sliderWidth);
		prgVol.getTransforms().addAll(new Rotate(-90, 0, 0));
		prgVol.setStyle("-fx-accent:blue");
		prgVol.setPadding(new Insets(0, 7, 30, 7));
		Group grpVol = new Group();
		grpVol.getChildren().add(prgVol);
		StackPane spVol = new StackPane();
		spVol.getChildren().addAll(grpVol, sldVol);
	    //VOLUME

		//FREQUENCY
		sldFreq = sldMaker.createFSlider();
		prgFreq = new ProgressBar(0);
		prgFreq.setMinWidth(sliderWidth);
		prgFreq.setMaxWidth(sliderWidth);
		prgFreq.getTransforms().addAll(new Rotate(-90, 0, 0));
		prgFreq.setPadding(new Insets(0, 7, 0, 7));
		prgFreq.setStyle("-fx-accent:blue");
		Group grpFreq = new Group();
		grpFreq.getChildren().add(prgFreq);
		StackPane spFreq = new StackPane();
		spFreq.getChildren().addAll(grpFreq, sldFreq);
		//FREQUENCY
		
		//RATE
		sldRate = sldMaker.createRSlider();
		prgRate = new ProgressBar(0);
		prgRate.setMinWidth(sliderWidth);
		prgRate.setMaxWidth(sliderWidth);
		prgRate.getTransforms().addAll(new Rotate(-90, 0, 0));
		prgRate.setPadding(new Insets(0, 7, 60, 7));
		prgRate.setStyle("-fx-accent:blue");
		Group grpRate = new Group();
		grpRate.getChildren().add(prgRate);
		StackPane spRate = new StackPane();
		spRate.getChildren().addAll(grpRate, sldRate);
		//
		
		//Disable sliders before file is loaded
		sldRate.setDisable(true);
		prgRate.setDisable(true);
		
		sldVol.setDisable(true);
		prgVol.setDisable(true);
		
		sldFreq.setDisable(true);
		prgFreq.setDisable(true);
		
		/**
		 * The observers for the sliders follows:
		 */
		//FREQUENCY
		sldFreq.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	prgFreq.setProgress(new_val.doubleValue()/19980);
            }
        }));
		
		//VOLUME
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
		
		//RATE
		sldRate.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	
            	prgRate.setProgress(new_val.doubleValue()/100);
            	try {
            		mp.setRate((double)new_val);
            		lblRate.textProperty().setValue(String.valueOf(sldRate.getValue()));
            		System.out.println(sldRate.getValue());
	            } catch (NullPointerException e1) {
		            System.out.println("NPE relating to rate slider (MediaPlayer)");
		        }
            	
            }
        }));
		
		//Create labels
		lblVol = lblMaker.createVLabel();
		lblVol.setPadding(new Insets(0, 30, 0, 0));
		lblVol.textProperty().bind(Bindings.format("%.0f", sldVol.valueProperty()));
		lblVol.setStyle(strLblColorCSS);
		
		lblFreq = lblMaker.createFLabel();
		lblFreq.textProperty().bind(Bindings.format("%.0f", sldFreq.valueProperty()));
		lblFreq.setStyle(strLblColorCSS);
		
		lblRate = lblMaker.createRLabel();
		DoubleProperty d = sldRate.valueProperty();
		String s = String.valueOf(d.doubleValue());
		lblRate.textProperty().setValue(s);	
		lblRate.setStyle(strLblColorCSS);

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
		
		VBox rSliderVB = new VBox();
		rSliderVB.getChildren().addAll(spRate, lblRate);
		rSliderVB.setSpacing(10);
		rSliderVB.setAlignment(Pos.CENTER);
		rSliderVB.setMinWidth(vbWidth);
		rSliderVB.setMaxWidth(vbWidth);
		
		//Create HBox for holding slider containers
		HBox sliderHB = new HBox();
		sliderHB.getChildren().addAll(vSliderVB, fSliderVB, rSliderVB);
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
		primaryScene = new Scene(root, 1000, 700, Color.web("#2c2f33"));
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
		ExtensionFilter fileFilter = new ExtensionFilter("Audio Files", "*.mp3", "*.m4a", "*.wav");
		fileChooser.getExtensionFilters().add(fileFilter);
        fileSelected = fileChooser.showOpenDialog(s);
        Media sound = new Media(new File(fileSelected.getAbsolutePath()).toURI().toString());
        return sound;
	}
	
	private void saveClick() {
		
	}
	
	private void saveAsClick() {
		
	}
	
	private void exitClick() {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Confirm Exit");
		confirm.setHeaderText("Are you sure you want to close the program?");
		Optional<ButtonType> result = confirm.showAndWait();
		if(result.get() == ButtonType.OK) System.exit(0);
		
	}
	
	private void themeDefClick() {
		primaryScene.setFill(Paint.valueOf("#2c2f33"));
		cmiThemeDef.selectedProperty().set(true);
    	cmiTheme1.selectedProperty().set(false);
    	cmiTheme2.selectedProperty().set(false);
    	cmiTheme3.selectedProperty().set(false);
	}
	
	private void theme1Click() {
		primaryScene.setFill(Paint.valueOf("red"));
		cmiThemeDef.selectedProperty().set(false);
    	cmiTheme1.selectedProperty().set(true);
    	cmiTheme2.selectedProperty().set(false);
    	cmiTheme3.selectedProperty().set(false);
	}
	
	private void theme2Click() {
		primaryScene.setFill(Paint.valueOf("blue"));
		cmiThemeDef.selectedProperty().set(false);
    	cmiTheme1.selectedProperty().set(false);
    	cmiTheme2.selectedProperty().set(true);
    	cmiTheme3.selectedProperty().set(false);
	}
	
	private void theme3Click() {
		primaryScene.setFill(Paint.valueOf("green"));
		cmiThemeDef.selectedProperty().set(false);
    	cmiTheme1.selectedProperty().set(false);
    	cmiTheme2.selectedProperty().set(false);
    	cmiTheme3.selectedProperty().set(true);
	}
	
}
