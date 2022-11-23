package easv.mrs.GUI.Controller;

import easv.mrs.BE.Movie;
import easv.mrs.GUI.Model.MovieModel;
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
    private Movie movie;
    @FXML
    private TextField txtTitle, txtYear;
    @FXML
    private Button btnUpdate;


    public void handleUpdate() throws Exception {
        //Get handle from the stage
        Stage stage = (Stage) btnUpdate.getScene().getWindow();

        //DO SOMETHING

        String newTitle = txtTitle.getText();
        int newYear = Integer.parseInt(txtYear.getText());
        movie.setTitle(newTitle);
        movie.setYear(newYear);

        try {
            movieModel.updateMovie(movie);
            movieModel.searchMovie("");
        } catch (Exception e) {
            displayError(e);
        }

        //Close the window
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        movie = MovieModel.selectedMovie;
        txtTitle.setText(movie.getTitle());
        txtYear.setText(String.valueOf(movie.getYear()));
    }

    private void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }
}
