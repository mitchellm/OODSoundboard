package src.menu_items;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javax.swing.JOptionPane;

public class About implements MI {
	public MenuItem create() {
		MenuItem mi = new MenuItem();
		mi.setText("About");
		mi.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	aboutClick();
            }
        });
		return mi;
	}
	
	private void aboutClick() {
		JOptionPane.showMessageDialog(null, "Develepors:\nJared Dean\nBrett Davis\nCorey Mccoy\nMitchell Murphy\nDavid Menear\nShane Kruse");
	}
}
