package photos.control;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The SlideShowController class controls the image slide show for an album in the photo library application.

 */
public class SlideShowController {

    @FXML
    private ImageView imageView;

    private int pointer;
    private ArrayList<Photo> photoList;

    /**
     * Initializes the slide show with the specified starting photo.
     *
     * @param count the index of the starting photo in the album's photo list
     */
    public void start(int count) {
        pointer = count;
        Album a = User.getCurrentSessionAlbum();
        ArrayList<Photo> photos = a.getPhotoList();
        photoList = photos;
        imageView.setImage(new Image("file:" + photoList.get(pointer).getImagePath()));
        imageView.setPreserveRatio(true);
    }

    /**
     * Displays the next photo in the album's photo list.
     *
     * @param event the ActionEvent object that triggered this method
     */
    @FXML
    public void next(ActionEvent event) {
        if (photoList.size() > 1) {
            pointer++;
            if (pointer == photoList.size()) {
                pointer = 0;
            }
            imageView.setImage(new Image("file:" + photoList.get(pointer).getImagePath()));
            imageView.setPreserveRatio(true);
        } else {
            Alert confirm1 = new Alert(Alert.AlertType.ERROR);
            confirm1.setTitle("One Photo!!!");
            confirm1.setContentText("This album has only 1 photo!!!");
            confirm1.setHeaderText(null);
            confirm1.setResizable(false);
            confirm1.getButtonTypes().setAll(ButtonType.OK);
            confirm1.showAndWait();
        }

    }

    /**
     * Displays the previous photo in the album's photo list.
     *
     * @param event the ActionEvent object that triggered this method
     */
    @FXML
    public void prev(ActionEvent event) {
        if (photoList.size() > 1) {
            pointer--;
            if (pointer == -1) {
                pointer = photoList.size() - 1;
            }
            imageView.setImage(new Image("file:" + photoList.get(pointer).getImagePath()));
            imageView.setPreserveRatio(true);
        } else {
            Alert confirm1 = new Alert(Alert.AlertType.ERROR);
            confirm1.setTitle("One Photo!!!");
            confirm1.setContentText("This album has only 1 photo!!!");
            confirm1.setHeaderText(null);
            confirm1.setResizable(false);
            confirm1.getButtonTypes().setAll(ButtonType.OK);
            confirm1.showAndWait();
        }
    }

    /**
     * Exits the slide show and returns to the album view.
     *
     * @param event the ActionEvent object that triggered this method
     */
    @FXML
    public void exitSS(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/OpenAlbum.fxml"));
            Parent openAlbum = loader.load();
            OpenAlbumController controller = loader.getController();
            Scene openAlbumScene = new Scene(openAlbum);
            controller.start();
            Stage mainStage = (Stage) imageView.getScene().getWindow();
            mainStage.setScene(openAlbumScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs out the current user and returns to the login view.
     *
     * @param event the ActionEvent object that triggered this method
     */
    @FXML
    public void logOut(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
            Scene loginScene = new Scene(login);
            Stage mainStage = (Stage) imageView.getScene().getWindow();
            mainStage.setScene(loginScene);
        }
   	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}