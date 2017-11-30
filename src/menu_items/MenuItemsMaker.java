package menu_items;

import javafx.scene.control.MenuItem;

public class MenuItemsMaker {
	private MI open;
	private MI exit;
	private MI about;
	
	public MenuItemsMaker() {
		open = new Open();
		exit = new Exit();
		about = new About();
	}
	
	public MenuItem createOpen() {
		return open.create();
	}
	
	public MenuItem createExit() {
		return exit.create();
	}
	
	public MenuItem createAbout() {
		return about.create();
	}
	
}
