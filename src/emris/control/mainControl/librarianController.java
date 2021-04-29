package emris.control.mainControl;

import emris.control.ControllerHandler;
import javafx.fxml.FXML;

import java.io.IOException;

public class librarianController extends ControllerHandler {
    @FXML
    void goToReader() throws IOException {
        changeScene("/emris/control/readerControl/readerFxml/reader_.fxml");

    }
    @FXML
    void goToBook() throws IOException {
        changeScene("/emris/control/bookControl/book_frame.fxml");

    }
}
