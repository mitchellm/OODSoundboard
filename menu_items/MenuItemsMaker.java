package menu_items;

import javafx.scene.control.MenuItem;

public class MenuItemsMaker {
	private MI save;
	private MI open;
	private MI saveAs;
	private MI exit;
	private MI themes;
	private MI about;
	
	public MenuItemsMaker() {
		save = new Save();
		open = new Open();
		saveAs = new SaveAs();
		exit = new Exit();
		themes = new Theme();
		about = new About();
	}
	
	public MenuItem createSave() {
		return save.create();
	}
	
	public MenuItem createOpen() {
		return open.create();
	}
	
	public MenuItem createSaveAs() {
		return saveAs.create();
	}
	
	public MenuItem createExit() {
		return exit.create();
	}
	
	public MenuItem createThemes() {
		return themes.create();
	}
	
	public MenuItem createAbout() {
		return about.create();
	}
	
}
