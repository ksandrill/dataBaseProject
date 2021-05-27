package emris.control.readerControl;

import emris.Constant;
import emris.control.ControllerHandler;
import emris.control.librarianControl.AddLibrarianController;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ReaderHandler extends ControllerHandler {
    String name;
    String surname;
    String password; //FIXME


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

    public void setPassword(String password)
    {
        this.password = password;
    }

    protected void createReaderUserDB() throws SQLException
    {
        ResultSet set = session.executeQuery("select \"id\" from " + Constant.adminName + ".\"reader\" where rownum = 1 order by \"id\" desc");
        int id = 0;
        if(set.next())
            id = set.getInt("id");

        String createUser = "create user \"18208_LIB_" + id + "\" identified by \"" + password + "\"";
        String grantUser = "grant create session to \"18208_LIB_" + id + "\" with admin option";
        String grantRole = "grant lib_reader to \"18208_LIB_" + id + "\" with admin option";

        AddLibrarianController.createDBData(createUser, grantUser, grantRole, session.getConnection());
    }
}
