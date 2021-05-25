package emris.control.bookControl;

import emris.Constant;
import emris.control.ControllerHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class addCompositionController extends ControllerHandler {

    @FXML
    private TextField authorField;
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<String> genreBox;

    @FXML
    void createBtn() throws SQLException, IOException {
        String procedure = "begin" + Constant.adminName + ".\"add_composition\"(?,?,?,?); end;";
        CallableStatement cs = session.getConnection().prepareCall(procedure);
        cs.setString(1, nameField.getText());
        cs.setString(2, authorField.getText());
        cs.setString(3, (genreBox.getSelectionModel().getSelectedItem()));
        cs.setInt(4, handledInt);
        session.executeTrans(cs);
        cancelBtn();

    }

    @FXML
    void cancelBtn() throws IOException {
        changeScene("/emris/control/bookControl/composition_frame.fxml", handledStr, handledInt);
    }

    @FXML
    void updateBox() throws SQLException {
        ArrayList<String> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("select" + Constant.adminName + ".\"composition_type\".\"type\" as type_name from" + Constant.adminName + ".\"composition_type\"");
        while (ret.next()) {
            auxList.add(ret.getString("type_name"));
        }
        genreBox.setItems(FXCollections.observableArrayList(auxList));


    }


}
