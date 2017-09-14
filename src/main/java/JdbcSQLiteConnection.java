import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSQLiteConnection {

    private Statement statement;

    public JdbcSQLiteConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:MyCalendar.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                statement = conn.createStatement();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void add(String date, String time, String title, String place, String note, String repeat){
        String sql = "insert into calendar values("+date+","+time+","+title+","+
                place+","+note+","+repeat+")";
        try{
            statement.executeUpdate(sql+"");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void show(String date, TextArea textArea){
        String sql = "Select Time, Title, Place, Note from Calendar where date = "+
                "\'"+date+"\'";
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            textArea.clear();
            while (resultSet.next()) {
                String time = resultSet.getString(1);
                String title = resultSet.getString(2);
                String place = resultSet.getString(3);
                String note = resultSet.getString(4);

                textArea.appendText(time+"  ");
                textArea.appendText("Title: "+title+"   ");
                textArea.appendText("Place: "+place+"   ");
                textArea.appendText("Note: "+note+"\n");
            }
            if (textArea.getText().trim().equals("")) {
                textArea.setText("You do not have an event today.");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void delete(String date, String time, String ti, String pl, String no, String re){
        String sql = "Delete from Calendar where Date = "+"\'"+date+"\' AND Time = "+
                "\'"+time+"\' AND Title = "+ "\'"+ti+"\' AND Place = "+ "\'"+pl+"\' AND " +
                "Note = "+ "\'"+no+"\' AND Detail = "+ "\'"+re+"\'";
        try{
            statement.executeUpdate(sql+"");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    public Statement getStatement(){
        return statement;
    }

}