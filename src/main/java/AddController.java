/**
 * นางสาวมณีกานต์ ญาณวิสิฏฐ์ 5810405258
 */

import Model.DateCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class AddController {

    ObservableList<String> cHour = FXCollections.observableArrayList(
            "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20,",
            "21", "22", "23"
    );

    ObservableList<String> cMin = FXCollections.observableArrayList(
            "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20,", "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
            "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
            "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"
    );

    @FXML
    protected Label dateDe;

    @FXML
    protected TextField title;

    @FXML
    protected ComboBox startHour;
    @FXML
    protected ComboBox startMin;

    @FXML
    protected ComboBox endHour;
    @FXML
    protected ComboBox endMin;

    @FXML
    protected TextField place;

    @FXML
    protected TextField note;

    @FXML
    protected Button submit;

    protected String inputTitle;
    protected String inputPlace;
    protected String inputNote;

    protected String sHour;
    protected String sMin;
    protected String eHour;
    protected String eMin;

    private ArrayList ev;
    private JdbcSQLiteConnection db;
    private String dayy;

    @FXML
    public void initialize(){
        dayy = String.valueOf(Controller.convert(Controller.day));
        db = Controller.db;

        dateDe.setText(dayy);
        startHour.setItems(cHour);
        startMin.setItems(cMin);
        endHour.setItems(cHour);
        endMin.setItems(cMin);
    }

    @FXML
    protected void submitEvent(ActionEvent event){
        Button sub = (Button) event.getSource();
        Stage stage = (Stage) sub.getScene().getWindow();
        FXMLLoader loadMain = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        try{
            inputTitle = title.getText();
            inputPlace = place.getText();
            inputNote = note.getText();

            sHour = startHour.getValue()+"";
            sMin = startMin.getValue()+"";
            eHour = endHour.getValue()+"";
            eMin = endMin.getValue()+"";

            db.add("\'"+dayy+"\'", "\'"+sHour+':'+sMin+"-"+eHour+":"+eMin+"\'",
                    "\'"+inputTitle+"\'", "\'"+inputPlace+"\'", "\'"+inputNote+"\'");

            stage.setScene(new Scene((Parent) loadMain.load()));
        }catch (IOException event1){
            event1.printStackTrace();
        }


    }

}
