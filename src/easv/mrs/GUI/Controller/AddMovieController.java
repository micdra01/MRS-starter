package easv.mrs.GUI.Controller;

import easv.mrs.GUI.Model.MovieModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddMovieController implements Initializable {
    @FXML
    private TextField txtTitle, txtYear;
    @FXML
    private Button btnAddNew;
    private MovieModel movieModel;

    private void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    public void handleAddNew() throws Exception {
        int year = Integer.parseInt(txtYear.getText());
        String title = txtTitle.getText();

        try {
            movieModel.createNewMovie(year, title);
        } catch (Exception e) {
            displayError(e);
        }

        Stage stage = (Stage) btnAddNew.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAddNew.setDisable(true);

        txtTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null && !newValue.trim().isEmpty()) {
                    btnAddNew.setDisable(false);
                } else {
                    btnAddNew.setDisable(true);
                }
            }
        });
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }
}
