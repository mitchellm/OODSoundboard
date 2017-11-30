package themes;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ThemeOne implements Theme
{
	Scene scene;
	
	@Override
	public void changeTheme(Scene scene) 
	{
		scene.setFill(Color.web("#5C2018"));
		
	}
	
	
	
}
