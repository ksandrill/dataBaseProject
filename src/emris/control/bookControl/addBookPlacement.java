package emris.control.bookControl;

import emris.Constant;
import emris.control.ControllerHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class addBookPlacement extends ControllerHandler {
    @FXML
    TextField hallNumber;
    @FXML
    TextField rack;
    @FXML
    TextField shell;
    @FXML
    ComboBox<String> libraryBox;

    @FXML
    void updateBox() throws SQLException {
        ArrayList<String> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("select" + Constant.adminName + ".\"library\".\"name\" as lib_name from" + Constant.adminName + ".\"library\"");
        while (ret.next()) {
            auxList.add(ret.getString("lib_name"));
        }
        libraryBox.setItems(FXCollections.observableArrayList(auxList));
    }

    @FXML
    void add() throws SQLException, IOException {
        String procedure = "begin" + Constant.adminName + ".\"create_placement\"(?,?,?,?,?,?); end;";
        CallableStatement cs = session.getConnection().prepareCall(procedure);
        System.out.println(handledInt);
        cs.setString(1, libraryBox.getSelectionModel().getSelectedItem());
        cs.setInt(2, Integer.parseInt(hallNumber.getText()));
        cs.setInt(3, Integer.parseInt(rack.getText()));
        cs.setInt(4, Integer.parseInt(shell.getText()));
        cs.setInt(5, handledInt);
        cs.setString(6, Constant.free);
        session.executeTrans(cs);
        back();
    }

    @FXML
    void back() throws IOException {
        changeScene("/emris/control/bookControl/placement_frame.fxml",handledStr,handledInt);
    }


}
