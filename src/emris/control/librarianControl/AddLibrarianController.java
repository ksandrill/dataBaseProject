package emris.control.librarianControl;

import emris.Constant;
import emris.control.ControllerHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class AddLibrarianController extends ControllerHandler {
    @FXML
    TextField nameField;
    @FXML
    TextField surnameField;
    @FXML
    TextField password;
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

        ResultSet set = session.executeQuery("select \"id\" from " + Constant.adminName + ".\"librarian\" where rownum = 1 order by \"id\" desc");
        int id = 0;
        if (set.next())
            id = set.getInt("id");

        String createUser = "create user \"18208_LIB_" + id + "\" identified by \"" + password.getText() + "\"";
        String grantUser = "grant create session to \"18208_LIB_" + id + "\" with admin option";
        String grantRole = "grant lib_librarian to \"18208_LIB_" + id + "\" with admin option";

        createDBData(createUser, grantUser, grantRole, session.getConnection());
    }

    public static void createDBData(String createUser, String grantUser, String grantRole, Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try (Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            System.out.println(createUser);
            st.executeUpdate(createUser);
            st.executeUpdate(grantUser);
            st.executeUpdate(grantRole);
            connection.commit();
        } catch (Throwable e) {
            connection.rollback();
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
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
