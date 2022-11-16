package easv.mrs.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UpdateMovieController {
    @FXML
    private Button btnUpdate;

    public void handleUpdate(ActionEvent actionEvent) {
        //DO SOMETHING

        //Close the window
        Stage stage = (Stage) btnUpdate.getScene().getWindow();
        stage.close();
    }
}
