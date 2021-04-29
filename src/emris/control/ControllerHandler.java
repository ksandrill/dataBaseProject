package emris.control;

import emris.Session;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class ControllerHandler {
    protected Session session;
    protected Stage stage;
    protected Role role;


    public void changeScene(String fxmlSource) throws IOException {
        ///ave satanic(kill me please)
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlSource));
        Scene scene = new Scene(loader.load());
        Object controller = loader.getController();
        ((ControllerHandler) controller).setSession(session);
        ((ControllerHandler) controller).setStage(stage);
        ((ControllerHandler) controller).setRole(role);;
        stage.setScene(scene);
        stage.show();
    }

    public void backToMain() throws IOException {

        String fxml;
        switch (role) {
            case admin:
                fxml = "/emris/control/mainControl/main_frame.fxml";
                break;
            case librarian:
                fxml = "/emris/control/mainControl/librarian_frame.fxml";
                break;
            case manager:
                fxml = "/emris/control/mainControl/managerFrame.fxml";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }
        changeScene(fxml);

    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
