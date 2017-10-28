package menu_items;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class Themes implements MI {
	public MenuItem create() {
		MenuItem mi = new MenuItem();
		mi.setText("Themes");
		mi.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	saveClick();
            }
        });
		return mi;
	}
	
	private void saveClick() {
		
	}
}
