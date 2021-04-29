package emris.control.mainControl;

import emris.control.ControllerHandler;
import javafx.fxml.FXML;

import java.io.IOException;

public class managerController extends ControllerHandler {
    @FXML
    void goToLibrary() throws IOException {
        changeScene("/emris/control/libraryControl/library_frame.fxml");

    }

    @FXML
    void goToLibrarian() throws IOException {
        changeScene("/emris/control/librarianControl/librarian_frame.fxml");


    }
}
