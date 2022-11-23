package easv.mrs.GUI.Controller;

import easv.mrs.BE.Movie;
import easv.mrs.GUI.Model.MovieModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieViewController implements Initializable {

    @FXML
    private TextField txtMovieSearch;
    @FXML
    private ListView<Movie> lstMovies;
    @FXML
    private Button btnUpdate, btnDelete;

    private MovieModel movieModel;
    private Movie movie;

    public MovieViewController() {
        try {
            movieModel = new MovieModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lstMovies.setItems(movieModel.getObservableMovies());

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);


        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {

                movieModel.searchMovie(newValue);
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }
        });

        /**
         * Method to change the text in textfields every time a new movie is selected.
         * If I had text fields on the main view:
         * txtTitle.setText(newValue.getTitle());
         * txtYear.setText(String.valueOf(newValue.getYear()));
         *
         * Also used for disabling Update/Delete buttons when no movie is selected.
         */
        lstMovies.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>() {
            @Override
            public void changed(ObservableValue<? extends Movie> observable, Movie oldValue, Movie newValue) {
                if (newValue != null) {
                    //
                    btnUpdate.setDisable(false);
                    btnDelete.setDisable(false);

                }
                if (newValue == null) {
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);
                }
            }
        });
    }

    private void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    public void openCreateMovie() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/mrs/GUI/View/AddMovieView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add movie");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        AddMovieController controller = loader.getController();
        controller.setMovieModel(movieModel);
        stage.show();
    }

    public void openUpdateMovie() throws IOException {
        Movie movie = lstMovies.getSelectionModel().getSelectedItem();
        movieModel.setSelectedMovie(movie);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/mrs/GUI/View/UpdateMovieView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Update '" + movie.getId() + ": " + movie.getTitle() + " (" + movie.getYear() + ")'");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        UpdateMovieController controller = loader.getController();
        controller.setMovieModel(movieModel);
    }

    public void handleRefresh() throws Exception {
        movieModel.searchMovie("");
    }

    public void handleDeleteMovie() throws Exception {
        try {
            Movie movie = lstMovies.getSelectionModel().getSelectedItem();
            movieModel.deleteMovie(movie);
        } catch (Exception e) {
            displayError(e);
        }
        handleRefresh();
    }
}
