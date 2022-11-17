package easv.mrs.GUI.Controller;

import easv.mrs.BE.Movie;
import easv.mrs.GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateMovieController implements Initializable {
    private MovieModel movieModel;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtYear;
    @FXML
    private Button btnUpdate;


    public void handleUpdate(ActionEvent actionEvent) {
        //Get handle from the stage
        Stage stage = (Stage) btnUpdate.getScene().getWindow();

        //DO SOMETHING


        //Close the window
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtTitle.setText(MovieModel.selectedMovie.getTitle());
        txtYear.setText(String.valueOf(MovieModel.selectedMovie.getYear()));

    }
}
