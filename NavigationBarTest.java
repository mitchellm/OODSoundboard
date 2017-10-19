import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import for Menu, MenuBar, etc..
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class NavigationBarTest extends Application {
	
	
	public static void main(String[] args) {
		launch();
	}
	//its possible to make submenus Menus in menus
	//hover over a drop down of a another dropdown appears
	//example in eclipse run -> run as -> javaapp
	//dropdowns can be made checkable
	public void start(Stage primaryStage) {
			//creates navBar
	        MenuBar navBar = new MenuBar();
	        
	        //syntax to create more items to put in navbar
	        Menu navThemes = new Menu("Themes");
	        Menu navEdit = new Menu("Edit");
	        Menu navHelp = new Menu("Help");
	        
	        //syntax to add options to nav tabs
	        //MenuItem what appears in drop down when clicking Nav
	        MenuItem about = new MenuItem("About");
	        
	        //when button is clicked this happens
	        about.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	       
	            	aboutInfo();
	            }
	        });
	        //adds the drop down option to the help tab(navHelp)
	        navHelp.getItems().addAll(about);
	        
	        //add nav tabs to navbar
	        navBar.getMenus().addAll(navThemes, navEdit, navHelp);
	        
	        BorderPane border = new BorderPane();
	        border.setTop(navBar);
	        primaryStage.setScene(new Scene(border, 1300, 600));
	        primaryStage.setTitle("Navigation Bar");
	        primaryStage.setResizable(false);
	        primaryStage.show();
	}
	private void aboutInfo() {
		//whatever you want it to do when clicked
	}
}