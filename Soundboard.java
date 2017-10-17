import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Soundboard extends Application {
	
	Slider vSlider, fSlider;
	
	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) {
	        
	        SliderMaker sm = new SliderMaker();
	        vSlider = sm.createVSlider();
		fSlider = sm.createFSlider();
		
	        StackPane root = new StackPane();
	        root.getChildren().addAll(vSlider, fSlider);
	        primaryStage.setScene(new Scene(root, 300, 250));
	        primaryStage.setTitle("Project Test");
	        primaryStage.show();
	 }
}
