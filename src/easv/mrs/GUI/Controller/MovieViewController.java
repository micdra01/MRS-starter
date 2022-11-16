package easv.mrs.GUI.Controller;

import easv.mrs.BE.Movie;
import easv.mrs.GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieViewController implements Initializable {


    public TextField txtMovieSearch;
    public ListView<Movie> lstMovies;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtTYear;

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
        /**
         */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMovieView.fxml"));
        Parent root1 = (Parent) loader.load();
        loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Add movie");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    public void handleAddNew(ActionEvent actionEvent) throws Exception {

        int year = Integer.parseInt(txtTYear.getText());
        String title = txtTitle.getText();

        movieModel.createNewMovie(year, title);
    }
}
