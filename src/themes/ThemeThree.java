package themes;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ThemeThree implements Theme
{
	Scene scene;
	
	@Override
	public void changeTheme(Scene scene) 
	{
		scene.setFill(Color.web("#FBE8A6"));
		
	}
	
	
	
}
