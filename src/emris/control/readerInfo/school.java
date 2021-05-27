package emris.control.readerInfo;

import emris.Constant;
import emris.control.readerControl.ReaderHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class school extends ReaderHandler {
    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField school;
    @FXML
    TextField letter;
    @FXML
    TextField classNumber;
    @FXML
    void back() throws IOException {
        changeScene("/emris/control/readerControl/readerFxml/reader_.fxml");

    }

    @FXML
    void update() throws SQLException {
        ResultSet ret = session.executeQuery("select \"name\", \"surname\", \"class\", \"class_letter\", \"school_name\" from " + Constant.adminName + ".\"school_student\", " + Constant.adminName + ".\"reader\" where \"school_student\".\"id\" = " + handledInt);
        ret.next();
        name.setText(ret.getString("name"));
        surname.setText(ret.getString("surname"));
        school.setText(ret.getString("school_name"));
        classNumber.setText(ret.getString("class"));
        letter.setText(ret.getString("class_letter"));




    }
}
