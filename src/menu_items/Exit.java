package menu_items;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class Exit implements MI {
	public MenuItem create() {
		MenuItem mi = new MenuItem();
		mi.setText("Exit");
		mi.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	exitClick();
            }
		});
		return mi;
	}
		
	private void exitClick() {
		System.exit(0);	
	}
}
