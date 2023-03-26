
package UI_Layer;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GUI_Main extends Application {
	double x,y;
	@Override
	public void start(Stage stage) {	
		try {
			Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
			Scene scene =  new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Welcome Page");
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
