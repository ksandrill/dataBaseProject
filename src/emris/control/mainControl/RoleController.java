package emris.control.mainControl;

import emris.control.ControllerHandler;
import emris.control.Role;
import javafx.fxml.FXML;

import java.io.IOException;

public class RoleController extends ControllerHandler {
    @FXML
    void managerIn() throws IOException {
        role = Role.manager;
        changeScene("/emris/control/mainControl/managerFrame.fxml");

    }

    @FXML
    void librarianIn() throws IOException {
        role = Role.librarian;
        changeScene("/emris/control/mainControl/librarian_frame.fxml");

    }

    @FXML
    void adminIn() throws IOException {
        role = Role.admin;
        changeScene("/emris/control/mainControl/main_frame.fxml");

    }

}
