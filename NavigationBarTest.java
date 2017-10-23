import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import for Menu, MenuBar, etc..
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
/*add to nav bar
	File-exit-open-save-saveas
	edit-themes
*/
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
	        Menu navFile = new Menu("File");
	        Menu navEdit = new Menu("Edit");
	        Menu navOptions = new Menu("Options");
	        Menu navHelp = new Menu("Help");
	        
	        //syntax to add options to nav tabs
	        //MenuItem what appears in drop down when clicking Nav
	        MenuItem about = new MenuItem("About");
	        MenuItem open = new MenuItem("Open");
	        MenuItem save = new MenuItem("Save");
	        MenuItem saveAs = new MenuItem("Save As");
	        MenuItem exit = new MenuItem("Exit");
	        MenuItem themes = new MenuItem("Themes");
	        
	        //when button is clicked this happens
	        about.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	            	aboutInfo();
	            }
	        });
	        open.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	       
	            	openClick();
	            }
				private void openClick() {
					//
				}
	        });
	        save.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	            	saveClick();
	            }

				private void saveClick() {
					
				}
	        });
	        saveAs.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	            	saveAsClick();
	            }
				private void saveAsClick() {
					
				}
	        });
	        exit.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	            	exitClick();
	            }

				private void exitClick() {
					
				}
	        });
	        themes.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	            	themesClick();
	            }
				private void themesClick() {
					
				}
	        });
	        //adds the drop down option to the help tab(navHelp)
	        navHelp.getItems().addAll(about);
	        navFile.getItems().addAll(open, save, saveAs, exit);
	        navEdit.getItems().addAll(themes);
	        navOptions.getItems().addAll();
	        
	        //add nav tabs to navbar
	        navBar.getMenus().addAll(navFile, navEdit, navOptions, navHelp);
	        
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
