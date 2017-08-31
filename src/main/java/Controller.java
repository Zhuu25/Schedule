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
    private static ArrayList event = new ArrayList();

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

    //ยังไม่สามารถแอดอีเว้นต์ได้มากกว่า1งาน
    @FXML
    protected void show(ActionEvent e){
        day = date.getValue();

        if (event.size() > 0){
//            Collections.sort(event);        //ไม่สามารถsortได้
            ArrayList<String[]> sorted = event;

            String string = String.valueOf(Controller.convert(Controller.day));
            for (int i = 0; i < sorted.size(); i++){
                if (string.equals(sorted.get(i)[0])){
                    textArea.appendText(sorted.get(i)[1]+"     ");
                    textArea.appendText("Title: "+sorted.get(i)[2]+"     ");
                    textArea.appendText("Place: "+sorted.get(i)[3]+"     ");
                    textArea.appendText("Note: "+sorted.get(i)[4]);
                    textArea.appendText("\n");

                }
                else if (i == sorted.size()-1){
                    textArea.setText("You do not have event on this day.");
                }
            }
        }
        else{
            textArea.setText("You do not have event on this day.");
        }
        pane.opacityProperty().setValue(1);

    }

    public static ArrayList getEvent() {
        return event;
    }

    protected static String convert(LocalDate da){
        return da.getDayOfMonth()+" "+da.getMonth()+" "+da.getYear();
    }
}
