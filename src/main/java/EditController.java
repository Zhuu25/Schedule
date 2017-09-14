import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditController {

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

    ObservableList<String> rep = FXCollections.observableArrayList(
            "None", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday", "Sunday", "Everyday", "Every month"
    );

    private JdbcSQLiteConnection db;
    private String dayy;
    private Statement statement;
    private ObservableList ed;

    private String oDay;
    private String oTime;
    private String oTitle;
    private String oPlace;
    private String oNote;
    private String oRepeat;

    @FXML
    private DatePicker da;

    @FXML
    private Label date;

    @FXML
    private ComboBox editEvent;

    @FXML
    private ComboBox repeat;

    @FXML
    private ComboBox sHour;

    @FXML
    private ComboBox sMin;

    @FXML
    private ComboBox eHour;

    @FXML
    private ComboBox eMin;

    @FXML
    private TextField title;

    @FXML
    private TextField place;

    @FXML
    private TextField note;

    @FXML
    public void initialize(){
        repeat.setItems(rep);
        sHour.setItems(cHour);
        sMin.setItems(cMin);
        eHour.setItems(cHour);
        eMin.setItems(cMin);
        dayy = String.valueOf(Controller.convert(Controller.day));
        oDay = dayy;
        date.setText(dayy);
        db = Controller.db;
        statement = db.getStatement();
        String sql = "Select Time, Title from Calendar where date = "+
                "\'"+dayy+"\'";
        da.setValue(Controller.day);
        ed = FXCollections.observableArrayList();
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            int a = 0;
            while (resultSet.next()) {
                String time = resultSet.getString(1);
                String title = resultSet.getString(2);
                ed.add(a, time+" "+title);
                a+=1;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        editEvent.setItems(ed);
    }

    @FXML
    protected void okSubmit(ActionEvent event){
        String str = editEvent.getValue().toString();
        String ti = str.substring(12);
        String sql = "Select Time, Title, Place, Note , Detail from Calendar where Date = "+"\'"+dayy+"\' AND Title = "+
                "\'"+ti+"\'";
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            int a = 0;
            while (resultSet.next()) {
                String repea = resultSet.getString(5);
                repeat.setValue(repea);
                String time = resultSet.getString(1);
                String title = resultSet.getString(2);
                sHour.setValue(time.substring(0, 2));
                sMin.setValue(time.substring(3, 5));
                eHour.setValue(time.substring(6, 8));
                eMin.setValue(time.substring(9));
                String place = resultSet.getString(3);
                String note = resultSet.getString(4);

                oTime = time;
                oTitle = title;
                oPlace = place;
                oNote = note;
                oRepeat = repea;

                this.title.setText(title);
                this.place.setText(place);
                this.note.setText(note);
                ed = FXCollections.observableArrayList();
                ed.add(a, time+" "+title);
                a += 1;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    protected void deleteEvent(ActionEvent event){
        Button sub = (Button) event.getSource();
        Stage stage = (Stage) sub.getScene().getWindow();
        FXMLLoader loadMain = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        try{
            db.delete(oDay+"", oTime+"", oTitle+"", oPlace+"", oNote+"", oRepeat+"");
            stage.setScene(new Scene((Parent) loadMain.load()));
        }catch (IOException event1){
            event1.printStackTrace();
        }
    }

    @FXML
    protected void submitEvent(ActionEvent event){
        Button sub = (Button) event.getSource();
        Stage stage = (Stage) sub.getScene().getWindow();
        FXMLLoader loadMain = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        try{
            db.delete(oDay+"", oTime+"", oTitle+"", oPlace+"", oNote+"", oRepeat+"");
            String datee = String.valueOf(Controller.convert(da.getValue()));
            String time = sHour.getValue()+":"+sMin.getValue()+"-"+eHour.getValue()+":"+eMin.getValue();
            String ti = title.getText();
            String pl = place.getText();
            String no = note.getText();
            String re = repeat.getValue().toString();
            db.add("\'"+datee+"\'", "\'"+time+"\'", "\'"+ti+"\'", "\'"+pl+"\'",
                    "\'"+no+"\'","\'"+re+"\'");
            stage.setScene(new Scene((Parent) loadMain.load()));
        }catch (IOException event1){
            event1.printStackTrace();
        }
    }

    @FXML
    protected void backHome(ActionEvent event){
        Button sub = (Button) event.getSource();
        Stage stage = (Stage) sub.getScene().getWindow();
        FXMLLoader loadMain = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        try{
            stage.setScene(new Scene((Parent) loadMain.load()));
        }catch (IOException event1){
            event1.printStackTrace();
        }
    }
}
