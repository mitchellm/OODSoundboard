package menu_items;

import javafx.scene.control.MenuItem;

public class Save implements MI {
	public MenuItem create() {
		MenuItem mi = new MenuItem();
		mi.setText("Save");
		return mi;
	}
}
