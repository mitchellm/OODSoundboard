package menu_items;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class Theme implements MI {
	public MenuItem create() {
		MenuItem mi = new MenuItem();
		mi.setText("Theme");
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
