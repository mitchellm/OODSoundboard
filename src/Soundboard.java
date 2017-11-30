import java.io.File;
import java.util.Optional;

import javax.swing.JOptionPane;

import buttons.ButtonMaker;
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
import labels.LabelMaker;
import menu_items.MenuItemsMaker;
import sliders.SliderMaker;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import themes.Factory;
import themes.Theme;

public class Soundboard extends Application {

	private File fileSelected;
	private MyAudioPlayer mp;
	
	Slider sldVol, sldRate, sldBal;
	Label lblVol, lblRate, lblBal;
	public ProgressBar prgVol, prgRate, prgBal;
	Factory factory = new Factory();
	
	public Button btnPlay, btnStop, btnPause;
	MenuItem mniAbout, mniExit, mniOpen;
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
        Menu navHelp = new Menu("Help");
        Menu navThemes = new Menu("Themes");
        
        //MenuItem what appears in drop down when clicking Nav
        mniAbout = miMaker.createAbout();
        mniOpen = miMaker.createOpen();
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
            		mp = new MyAudioPlayer(openClick(mp, primaryStage));
	            	btnPlay.setDisable(false);
	        		btnPause.setDisable(false);
	        		btnStop.setDisable(false);
	        		btnMute.setDisable(false);
	        		
	        		sldVol.setDisable(false);
	        		prgVol.setDisable(false);
	        		
	        		prgRate.setDisable(false);
	        		sldRate.setDisable(false);
	        		
	        		sldBal.setDisable(false);
	        		prgBal.setDisable(false);
            	} catch(NullPointerException e) {
            		System.out.println("No file selected.");
            	}
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
        navFile.getItems().addAll(mniOpen, mniExit);
        navEdit.getItems().addAll(navThemes);
        navThemes.getItems().addAll(cmiThemeDef, cmiTheme1, cmiTheme2, cmiTheme3);
        
        //add nav tabs to navbar
        navBar.getMenus().addAll(navFile, navEdit, navHelp);
        /* ---END NAVBAR--- */

        /* ---START BUTTONS--- */
        btnPlay = btnMaker.createPlayButton();
        btnPlay.setStyle("-fx-font: 22 arial; -fx-base: #D9B08C;");
        btnPause = btnMaker.createPauseButton();
        btnPause.setStyle("-fx-font: 22 arial; -fx-base: #D9B08C;");
		btnStop = btnMaker.createStopButton();
		btnStop.setStyle("-fx-font: 22 arial; -fx-base: #D9B08C;");
		
		btnMute = new ToggleButton();
		btnMute.setText("Mute");
		btnMute.setStyle("-fx-font: 22 arial; -fx-base: #D9B08C;");
		
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
		prgVol.setPadding(new Insets(0, 7, 0, 7));
		Group grpVol = new Group();
		grpVol.getChildren().add(prgVol);
		StackPane spVol = new StackPane();
		spVol.getChildren().addAll(grpVol, sldVol);
	    //VOLUME
		
		//RATE
		double rateInitial = 1;
		sldRate = sldMaker.createRSlider();
		sldRate.setValue(rateInitial);
		prgRate = new ProgressBar((rateInitial-0.5)/1.5);
		prgRate.setMinWidth(sliderWidth);
		prgRate.setMaxWidth(sliderWidth);
		prgRate.getTransforms().addAll(new Rotate(-90, 0, 0));
		prgRate.setPadding(new Insets(0, 7, 0, 7));
		prgRate.setStyle("-fx-accent:blue");
		Group grpRate = new Group();
		grpRate.getChildren().add(prgRate);
		StackPane spRate = new StackPane();
		spRate.getChildren().addAll(grpRate, sldRate);
		//	
		
		//BALANCE
		sldBal = sldMaker.createBSlider();
		sldBal.setValue(0);
		prgBal = new ProgressBar(0.5);
		prgBal.setMinWidth(sliderWidth);
		prgBal.setMaxWidth(sliderWidth);
		prgBal.getTransforms().addAll(new Rotate(-90, 0, 0));
		prgBal.setStyle("-fx-accent:blue");
		prgBal.setPadding(new Insets(0, 7, 0, 7));
		Group grpBal = new Group();
		grpBal.getChildren().add(prgBal);
		StackPane spBal = new StackPane();
		spBal.getChildren().addAll(grpBal, sldBal);
		
		//Disable sliders before file is loaded
		sldRate.setDisable(true);
		prgRate.setDisable(true);
		
		sldVol.setDisable(true);
		prgVol.setDisable(true);
		
		sldBal.setDisable(true);
		prgBal.setDisable(true);
		
		/**
		 * The observers for the sliders follows:
		 */
		
		//VOLUME
		
		sldVol.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	
            	prgVol.setProgress(new_val.doubleValue()/100);
            	try {
            		mp.setVolume(((double)new_val/100.0));
	            } catch (NullPointerException e1) {
		            System.out.println("NPE relating to volume slider (MediaPlayer)");
		        }
            	
            }
        }));
		
		//RATE
		
		sldRate.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	prgRate.setProgress((new_val.doubleValue()-0.5)/1.5);
            	try {
            		mp.setRate(((double)new_val));
	            } catch (NullPointerException e1) {
		            System.out.println("NPE relating to rate slider (MediaPlayer)");
		        }
            	
            }
        }));
		
		//prgRate.progressProperty().bind(sldRate.valueProperty().divide(100));
		
		//
		
		//BALANCE
		
		
		sldBal.valueProperty().addListener((new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	prgBal.setProgress((new_val.doubleValue()+1)/2);
            	try {
            		mp.setBalance(new_val.doubleValue());
	            } catch (NullPointerException e1) {
		            System.out.println("NPE relating to rate slider (MediaPlayer)");
		        }
            	
            }
        }));
		//
		
		//Create labels
		
		//VOLUME
		lblVol = lblMaker.createVLabel();
		lblVol.textProperty().bind(Bindings.format("%.0f", sldVol.valueProperty()));
		lblVol.setStyle(strLblColorCSS);
		//
		
		//RATE
		lblRate = lblMaker.createRLabel();
		lblRate.textProperty().bind(Bindings.format("%.1f", sldRate.valueProperty()));
		lblRate.setStyle(strLblColorCSS);
		//
		
		//BALACNE
		lblBal = lblMaker.createBLabel();
		lblBal.textProperty().bind(Bindings.format("%.1f", sldBal.valueProperty()));
		lblBal.setStyle(strLblColorCSS);
		//
		
		//Create containers for holding sliders and labels		
		double vbWidth = 65;
		
		VBox vSliderVB = new VBox();
		vSliderVB.getChildren().addAll(spVol, lblVol);
		vSliderVB.setSpacing(10);
		vSliderVB.setAlignment(Pos.CENTER);
		vSliderVB.setMinWidth(vbWidth);
		vSliderVB.setMaxWidth(vbWidth);
		
		VBox rSliderVB = new VBox();
		rSliderVB.getChildren().addAll(spRate, lblRate);
		rSliderVB.setSpacing(10);
		rSliderVB.setAlignment(Pos.CENTER);
		rSliderVB.setMinWidth(vbWidth);
		rSliderVB.setMaxWidth(vbWidth);
		
		VBox bSliderVB = new VBox();
		bSliderVB.getChildren().addAll(spBal, lblBal);
		bSliderVB.setSpacing(10);
		bSliderVB.setAlignment(Pos.CENTER);
		bSliderVB.setMinWidth(vbWidth);
		bSliderVB.setMaxWidth(vbWidth);
		
		//Create HBox for holding slider containers
		HBox sliderHB = new HBox();
		sliderHB.getChildren().addAll(vSliderVB, rSliderVB, bSliderVB);
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
		JOptionPane.showMessageDialog(null, "Developers:\nJared Dean\nBrett Davis\nCorey Mccoy\nMitchell Murphy\nDavid Menear\nShane Kruse");
	}
	
	private Media openClick(MyAudioPlayer mp, Stage s) {
		FileChooser fileChooser = new FileChooser();
		ExtensionFilter fileFilter = new ExtensionFilter("Audio Files", "*.mp3", "*.m4a", "*.wav");
		fileChooser.getExtensionFilters().add(fileFilter);
        fileSelected = fileChooser.showOpenDialog(s);
        Media sound = new Media(new File(fileSelected.getAbsolutePath()).toURI().toString());
        return sound;
	}
	
	private void exitClick() {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Confirm Exit");
		confirm.setHeaderText("Are you sure you want to close the program?");
		Optional<ButtonType> result = confirm.showAndWait();
		if(result.get() == ButtonType.OK) System.exit(0);
		
	}
	
	// TEND TO THIS //
	
	private void themeDefClick() {
		primaryScene.setFill(Paint.valueOf("#2c2f33"));
		cmiThemeDef.selectedProperty().set(true);
    	cmiTheme1.selectedProperty().set(false);
    	cmiTheme2.selectedProperty().set(false);
    	cmiTheme3.selectedProperty().set(false);
	}
	
	private void theme1Click() {
		
		Theme theme1 = factory.getTheme("Red");
		btnPlay.setStyle("-fx-font: 22 arial; -fx-base: #F3E0DC;");
		btnStop.setStyle("-fx-font: 22 arial; -fx-base: #F3E0DC;");
		btnPause.setStyle("-fx-font: 22 arial; -fx-base: #F3E0DC;");
		btnMute.setStyle("-fx-font: 22 arial; -fx-base: #F3E0DC;");
		prgVol.setStyle("-fx-accent: #4285F4");
		prgRate.setStyle("-fx-accent: #4285F4");
		prgBal.setStyle("-fx-accent: #4285F4");
		theme1.changeTheme(primaryScene);
		cmiThemeDef.selectedProperty().set(false);
    	cmiTheme1.selectedProperty().set(true);
    	cmiTheme2.selectedProperty().set(false);
    	cmiTheme3.selectedProperty().set(false);
	}
	
	private void theme2Click() {
		Theme theme2 = factory.getTheme("Green");
		btnPlay.setStyle("-fx-font: 22 arial; -fx-base: #C5C6C7;");
		btnStop.setStyle("-fx-font: 22 arial; -fx-base: #C5C6C7;");
		btnPause.setStyle("-fx-font: 22 arial; -fx-base: #C5C6C7;");
		btnMute.setStyle("-fx-font: 22 arial; -fx-base: #C5C6C7;");
		prgVol.setStyle("-fx-accent: #B1A296");
		prgRate.setStyle("-fx-accent: #B1A296");
		prgBal.setStyle("-fx-accent: #B1A296");
		theme2.changeTheme(primaryScene);
		cmiThemeDef.selectedProperty().set(false);
    	cmiTheme1.selectedProperty().set(false);
    	cmiTheme2.selectedProperty().set(true);
    	cmiTheme3.selectedProperty().set(false);
	}
	
	private void theme3Click() {
		Theme theme3 = factory.getTheme("Blue");
		btnPlay.setStyle("-fx-font: 22 arial; -fx-base: #F4976C;");
		btnStop.setStyle("-fx-font: 22 arial; -fx-base: #F4976C;");
		btnPause.setStyle("-fx-font: 22 arial; -fx-base: #F4976C;");
		btnMute.setStyle("-fx-font: 22 arial; -fx-base: #F4976C;");
		prgVol.setStyle("-fx-accent: #3036C6");
		prgRate.setStyle("-fx-accent: #3036C6");
		prgBal.setStyle("-fx-accent: #3036C6");
		lblVol.setStyle("-fx-accent:black");
		lblRate.setStyle("-fx-accent:black");
		lblBal.setStyle("-fx-accent:black");
		theme3.changeTheme(primaryScene);
		cmiThemeDef.selectedProperty().set(false);
    	cmiTheme1.selectedProperty().set(false);
    	cmiTheme2.selectedProperty().set(false);
    	cmiTheme3.selectedProperty().set(true);
	}
	
	
}
