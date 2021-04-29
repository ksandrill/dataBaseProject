package emris.control.bookControl;

import emris.control.ControllerHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class addBookController extends ControllerHandler {
    @FXML
    ComboBox<String> bookTypeBox;
    @FXML
    TextField bookName;
    @FXML
    DatePicker dataPicker;

    @FXML
    void cancelBtn() throws IOException {
        changeScene("/emris/control/bookControl/book_frame.fxml");
    }

    @FXML
    void updateBox() throws SQLException {
        ArrayList<String> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("select \"book_type\".\"type\" as type_name from \"book_type\"");
        while (ret.next()) {
            auxList.add(ret.getString("type_name"));
        }
        bookTypeBox.setItems(FXCollections.observableArrayList(auxList));


    }

    @FXML
    void nextBtn() {
        String deleteProcedure = "begin \"create_book\"(?,?,?); end;";
        try {
            CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
            cs.setString(1, bookName.getText());
            cs.setDate(2, Date.valueOf(dataPicker.getValue()));
            cs.setString(3, (bookTypeBox.getSelectionModel().getSelectedItem()));
            cs.executeUpdate();
            cancelBtn();

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }


    }
}
