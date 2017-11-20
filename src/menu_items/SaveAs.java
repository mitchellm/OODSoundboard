package src.menu_items;

import javafx.scene.control.MenuItem;

public class SaveAs implements MI {
	public MenuItem create() {
		MenuItem mi = new MenuItem();
		mi.setText("Save As");
		return mi;
	}
}
