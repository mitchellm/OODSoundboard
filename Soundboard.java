import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Soundboard extends Application {
	
	Slider vSlider, fSlider;
	Label vl, fl;
	
	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) {
	        
	        SliderMaker sm = new SliderMaker();
		LabelMaker lm = new LabelMaker();
		
	        vSlider = sm.createVSlider();
		vl = lm.createVLabel();
	    	vl.textProperty().bind(
	    		Bindings.format("%.2f", vSlider.valueProperty())
	    	);
		
		fSlider = sm.createFSlider();
		fl = lm.createFLabel();
		fl.textProperty().bind(
			Bindings.format("%.2f", fSlider.valueProperty())
		);
		
	        StackPane root = new StackPane();
	        root.getChildren().addAll(vl, fl, vSlider, fSlider);
	        primaryStage.setScene(new Scene(root, 1300, 600));
	        primaryStage.setTitle("Project Test");
		primaryStage.setResizable(false);
	        primaryStage.show();
	}
}
