package easv.mrs.GUI.Controller;

import easv.mrs.GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMovieController {
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtYear;
    @FXML
    private Button btnAddNew;
    private MovieModel movieModel;

    public AddMovieController() {
        try {
            movieModel = new MovieModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    public void handleAddNew(ActionEvent actionEvent) {
        int year = Integer.parseInt(txtYear.getText());
        String title = txtTitle.getText();

        movieModel.createNewMovie(year, title);

        Stage stage = (Stage) btnAddNew.getScene().getWindow();
        stage.close();
    }
}
