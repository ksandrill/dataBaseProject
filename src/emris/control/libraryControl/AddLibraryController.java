package emris.control.libraryControl;

import emris.Constant;
import emris.control.ControllerHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class AddLibraryController extends ControllerHandler {
    @FXML
    TextField name;
    @FXML
    TextField addr;

    @FXML
    void addBtn() throws SQLException, IOException {
        String insertStr = "INSERT INTO" + Constant.adminName + ".\"library\"(\"name\",\"addr\") VALUES (?,?)";
        CallableStatement cs = session.getConnection().prepareCall(insertStr);
        cs.setString(1, name.getText());
        cs.setString(2, addr.getText());
        session.executeTrans(cs);
        changeScene("/emris/control/libraryControl/library_frame.fxml");

    }


    @FXML
    void cancelBtn() throws IOException {
        changeScene("/emris/control/libraryControl/library_frame.fxml");


    }

}
