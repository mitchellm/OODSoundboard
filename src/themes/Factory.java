package themes;
import javafx.scene.Scene;
public class Factory {

	Scene scene;
	public Theme getTheme(String themeType)
	{
		if(themeType == null)
		{
			return null;
		}
		if(themeType.equalsIgnoreCase("Red"))
		{
			return new ThemeOne();
		}
		if(themeType.equalsIgnoreCase("Green"))
		{
			return new ThemeTwo();
		}
		if(themeType.equalsIgnoreCase("Blue"))
		{
			return new ThemeThree();
		}
		return null;
	}
		

}
