package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.MediaPlayerMain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MediaPlayerController implements Initializable {
	@FXML
	ToolBar toolBar;

	@FXML
	CheckBox checkBox;

	@FXML
	Slider slider;

	@FXML
	Button btnPlay, btnPause, btnStop, btnOpen;

	@FXML
	BorderPane borderPane;

	private Media media;
	private MediaPlayer mediaPlayer;
	private MediaView mediaView;

	public void initialize(URL location, ResourceBundle resources) {
		borderPane.setCenter(createMediaView());
		createToolbar();
		MyEvent event = new MyEvent();
		btnPlay.setOnAction(event);
		btnPause.setOnAction(event);
		btnStop.setOnAction(event);
		btnOpen.setOnAction(event);
	}

	public void createToolbar() {
		toolBar = new ToolBar(btnPlay, btnPause, btnStop, btnOpen, slider, checkBox);
		borderPane.setBottom(toolBar);
	}

	public MediaView createMediaView() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Media files", "*.flv", "*.mp4", "*.mpeg"));
		File file = fc.showOpenDialog(null);
		String path = file.getAbsolutePath();
		path = path.replace("\\", "/");
		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView = new MediaView(mediaPlayer);
		mediaPlayer.setAutoPlay(true);
		return mediaView;
	}

	public void fullScreen(ActionEvent event) {
		if (checkBox.isSelected()) {
			MediaPlayerMain.getStage().setFullScreen(true);
			System.out.println("Fullscreen true");
		} else {
			MediaPlayerMain.getStage().setFullScreen(false);
			System.out.println("Fullscreen false");
		}
	}

	class MyEvent implements EventHandler<ActionEvent> {

		public void handle(ActionEvent event) {
			Button btn = (Button) event.getSource();
			if (btn.equals(btnPlay)) {
				mediaPlayer.play();
			} else if (btn.equals(btnPause)) {
				mediaPlayer.pause();
			} else if (btn.equals(btnStop)) {
				mediaPlayer.stop();
			} else if (btn.equals(btnOpen)) {
				if (mediaPlayer.getStatus().equals(Status.PLAYING)) {
					mediaPlayer.dispose();
					borderPane.setCenter(createMediaView());
					createToolbar();
				}
			}
			System.out.println(btn.getText());
		}

	}
}
