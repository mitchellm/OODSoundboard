import javafx.scene.control.Label;
import javafx.geometry.Insets;
public class VolumeLabel implements L {
	
	public Label create() {
		Label label = new Label("");
		Insets labelInsets = new Insets(500.0, 1225.0, 0.0, 0.0);
		label.setPadding(labelInsets);
		return label;
	}
	
}
