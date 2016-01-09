package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MediaPlayerMain extends Application {

	static Stage stage;
	
	@Override
	public void start(Stage stage) {
		try {
			MediaPlayerMain.stage = stage;
			URL url = getClass().getResource("/application/MediaPlayerView.fxml");
			BorderPane pane = FXMLLoader.load(url);
			Scene scene = new Scene(pane);
			stage.setTitle("Media Player");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage(){
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
