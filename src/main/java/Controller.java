/**
 * นางสาวมณีกานต์ ญาณวิสิฏฐ์ 5810405258
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    @FXML
    protected DatePicker date;

    @FXML
    protected BorderPane pane;

    @FXML
    protected TextArea textArea;

    @FXML
    protected Button showButton;

    @FXML
    protected static Button addButton;

    protected static LocalDate day;
    protected static JdbcSQLiteConnection db = new JdbcSQLiteConnection();


    @FXML
    protected void addEvent(ActionEvent event){
        day = date.getValue();
        Button sub = (Button) event.getSource();
        Stage stage = (Stage) sub.getScene().getWindow();
        FXMLLoader loadAdd = new FXMLLoader(getClass().getResource("AddPage.fxml"));
        try{
            stage.setScene(new Scene((Parent) loadAdd.load()));
        }catch (IOException event1){
            event1.printStackTrace();
        }
    }

    @FXML
    protected void edit(ActionEvent e){

    }

    @FXML
    protected void show(ActionEvent e){
        day = date.getValue();
        String dayy = String.valueOf(Controller.convert(day));
        db.show(dayy, textArea);
        pane.opacityProperty().setValue(1);

    }

    protected static String convert(LocalDate da){
        return da.getDayOfMonth()+" "+da.getMonth()+" "+da.getYear();
    }
}
