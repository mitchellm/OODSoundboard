package themes;

import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class ThemeTwo implements Theme
{
	Scene scene;
	
	@Override
	public void changeTheme(Scene scene) 
	{
		scene.setFill(Color.web("#7395AE"));	
	}
	
	
	
}
