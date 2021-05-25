package emris.control.loginControl;

import emris.Session;
import emris.control.ControllerHandler;
import emris.control.Role;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//    private final String host = "84.237.50.81";
//    private final int    port = 1521       ;
//    private final String sid  = "XE"       ;
public class LoginSceneController extends ControllerHandler {

    public void debug_shit() {
        host.setText("84.237.50.81");
        port.setText("1521");
        sid.setText("XE");
        login.setText("18208_shshelokov");
        password.setText("qwert098");


    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private TextField sid;
    @FXML
    private TextField port;
    @FXML
    private TextField host;

    @FXML
    private void buttonClicked() throws IOException, SQLException {
        session.createConnection(login.getText(), password.getText(), host.getText(), Integer.parseInt(port.getText()), sid.getText());
        ArrayList<String> roleList = getUserRoles();
        changeScene(getRoleFxml(roleList));
    }

    private String getRoleFxml(ArrayList<String> roleList) {
        String fxmlSource = "";
        for (String curRole : roleList) {
            if (curRole.equals("LIB_ADMIN")) {
                role = Role.admin;
                fxmlSource = "/emris/control/mainControl/main_frame.fxml";
                return fxmlSource;
            }
            if (curRole.equals("LIB_MANAGER")) {
                role = Role.manager;
                fxmlSource = "/emris/control/mainControl/managerFrame.fxml";
                return fxmlSource;
            }
            if (curRole.equals("LIB_LIBRARIAN")) {
                role = Role.librarian;
                fxmlSource = "/emris/control/mainControl/librarian_frame.fxml";
                return fxmlSource;
            }
        }
        return fxmlSource;


    }

    private ArrayList<String> getUserRoles() throws SQLException {
        ArrayList<String> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("SELECT \"USER_ROLE_PRIVS\".\"GRANTED_ROLE\" FROM \"USER_ROLE_PRIVS\"");
        while (ret.next()) {
            String curRole = ret.getString("GRANTED_ROLE");
            System.out.println(curRole);
            auxList.add(curRole);

        }
        return auxList;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
