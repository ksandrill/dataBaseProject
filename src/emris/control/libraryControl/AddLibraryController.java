package emris.control.libraryControl;

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
    void addBtn() {
        String insertStr = "INSERT INTO  \"library\"(\"name\",\"addr\") VALUES (?,?)";
        try {
            CallableStatement cs = session.getConnection().prepareCall(insertStr);
            cs.setString(1, name.getText());
            cs.setString(2, addr.getText());
            cs.executeUpdate();
            changeScene("/emris/control/librarianControl/library_frame.fxml");

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

    }



    @FXML
    void cancelBtn() throws IOException {
        changeScene("/emris/control/libraryControl/LibraryController.java");


    }

}
