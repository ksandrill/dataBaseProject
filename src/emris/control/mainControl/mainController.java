package emris.control.mainControl;

import emris.control.ControllerHandler;
import javafx.fxml.FXML;

import java.io.IOException;


public class mainController extends ControllerHandler {


    @FXML
    void goToReader() throws IOException {
        changeScene("/emris/control/readerControl/readerFxml/reader_.fxml");

    }

    @FXML
    void goToLibrary() throws IOException {
        changeScene("/emris/control/libraryControl/library_frame.fxml");

    }

    @FXML
    void goToLibrarian() throws IOException {
        changeScene("/emris/control/librarianControl/librarian_frame.fxml");


    }

    @FXML
    void goToBook() throws IOException {
        changeScene("/emris/control/bookControl/book_frame.fxml");

    }


}
