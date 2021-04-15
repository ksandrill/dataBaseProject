package emris;


import emris.control.loginControl.LoginSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class AppFX extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Session session = new Session();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/emris/control/loginControl/loginScene.fxml"));
        Scene scene = new Scene(loader.load());
        LoginSceneController loginSceneController = loader.getController();
        loginSceneController.setSession(session);
        loginSceneController.setStage(primaryStage);
        loginSceneController.debug_shit();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent we) ->
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Предупреждение");
            a.setHeaderText("Вы точно хотите закрыть приложение?");
            Optional<ButtonType> closeResponse = a.showAndWait();
            if (!ButtonType.OK.equals(closeResponse.get())) {
                we.consume();

            }else{
                try {
                    session.closeConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });


    }



}
