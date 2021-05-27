package emris.control.readerInfo;

import emris.control.readerControl.ReaderHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class scientist extends ReaderHandler {
    @FXML
    TextField name;
    @FXML
    TextField surname;

    @FXML
    TextField subject;
    @FXML
    TextField institution;
    @FXML
    void back() throws IOException {
        changeScene("/emris/control/readerControl/readerFxml/reader_.fxml");

    }

    @FXML
    void update(){

    }

}
