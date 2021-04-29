package emris.control.loginControl;

import emris.Session;
import emris.control.ControllerHandler;
import emris.control.mainControl.mainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private void buttonClicked() throws IOException {
        session.createConnection(login.getText(), password.getText(), host.getText(), Integer.parseInt(port.getText()), sid.getText());
        changeScene("/emris/control/mainControl/role_frame.fxml");
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
