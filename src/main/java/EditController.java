import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class EditController {

    private JdbcSQLiteConnection db;
    private String dayy;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    public void initialize(){
        db = Controller.db;
        dayy = String.valueOf(Controller.convert(Controller.day));
    }

    @FXML
    protected void deleteEvent(ActionEvent event){

    }

    @FXML
    protected void submitEvent(ActionEvent event){

    }
}
