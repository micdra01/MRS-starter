package easv.mrs.GUI.Controller;

import easv.mrs.BE.Movie;
import easv.mrs.GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieViewController implements Initializable {


    public TextField txtMovieSearch;
    public ListView<Movie> lstMovies;

    private MovieModel movieModel;

    public MovieViewController()  {

        try {
            movieModel = new MovieModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        lstMovies.setItems(movieModel.getObservableMovies());

        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                movieModel.searchMovie(newValue);
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }
        });

    }

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }


    public void search(KeyEvent keyEvent) {
    }

    public void openCreateMovie(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/mrs/GUI/View/AddMovieView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add movie");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void openUpdateMovie(ActionEvent actionEvent) throws IOException {
        Movie movie = lstMovies.getFocusModel().getFocusedItem();
        movieModel.setSelectedMovie(movie); //Save info about selected movie in the MovieModel to use in the next window

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/mrs/GUI/View/UpdateMovieView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Update '" + movie.getId() + ": " + movie.getTitle() + " (" + movie.getYear() + ")'");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
