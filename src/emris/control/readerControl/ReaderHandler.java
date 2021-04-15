package emris.control.readerControl;

import emris.control.ControllerHandler;
import javafx.fxml.FXML;

import java.io.IOException;

public abstract class ReaderHandler extends ControllerHandler {
    String name;
    String surname;


    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    String library;
    @FXML
    void cancelBtn() throws IOException {
       returnToReader();

    }

    void returnToReader() throws IOException {
        changeScene("/emris/control/readerControl/readerFxml/reader_.fxml");

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
