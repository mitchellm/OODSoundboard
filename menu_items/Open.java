package menu_items;

import javafx.scene.control.MenuItem;

public class Open implements MI {
	public MenuItem create() {
		MenuItem mi = new MenuItem();
		mi.setText("Open");
		return mi;
	}
}
