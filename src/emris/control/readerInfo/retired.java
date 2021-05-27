package emris.control.readerInfo;

import emris.Constant;
import emris.control.readerControl.ReaderHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class retired extends ReaderHandler {
    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField date;

    @FXML
    void back() throws IOException {
        changeScene("/emris/control/readerControl/readerFxml/reader_.fxml");

    }

    @FXML
    void update() throws SQLException {
        ResultSet ret = session.executeQuery("select \"name\", \"surname\", \"out_date\" from " + Constant.adminName + ".\"retired\", " + Constant.adminName + ".\"reader\" where \"retired\".\"id\" = " + handledInt);
        ret.next();
        name.setText(ret.getString("name"));
        surname.setText(ret.getString("surname"));
        date.setText(String.valueOf(ret.getDate("out_date")));

    }
}
