package emris.control.librarianControl;

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

public class AddLibrarianController extends ControllerHandler {
    @FXML
    TextField nameField;
    @FXML
    TextField surnameField;
    @FXML
    ComboBox<String> libraryBox;

    @FXML
    void cancelBtn() throws IOException {
        changeScene("/emris/control/librarianControl/librarian_frame.fxml");

    }

    @FXML
    void addBtn() throws SQLException, IOException {
        String deleteProcedure = "begin" + Constant.adminName + ".\"create_librarian\"(?,?,?); end;";
        CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
        cs.setString(1, nameField.getText());
        cs.setString(2, surnameField.getText());
        cs.setString(3, libraryBox.getSelectionModel().getSelectedItem());
        cs.executeUpdate();
        changeScene("/emris/control/librarianControl/librarian_frame.fxml");


    }

    @FXML
    void updateBox() throws SQLException {
        ArrayList<String> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("select \"library\".\"name\" as lib_name from \"library\"");
        while (ret.next()) {
            auxList.add(ret.getString("lib_name"));
        }
        libraryBox.setItems(FXCollections.observableArrayList(auxList));


    }


}
