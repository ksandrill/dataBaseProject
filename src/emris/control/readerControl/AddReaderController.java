package emris.control.readerControl;

import emris.Session;
import emris.control.ControllerHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddReaderController extends ControllerHandler implements Initializable {


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


    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    RadioButton scientistR;
    @FXML
    RadioButton workerR;
    @FXML
    RadioButton studentR;
    @FXML
    RadioButton retiredR;
    @FXML
    RadioButton scholarR;

    @FXML
    ComboBox<String> libraryBox;

    @FXML
    void updateBox() throws SQLException {
        ArrayList<String> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("select \"library\".\"name\" as lib_name from \"library\"");
        while (ret.next()) {
            auxList.add(ret.getString("lib_name"));
        }
        libraryBox.setItems(FXCollections.observableArrayList(auxList));


    }


    String fxmlSource;


    @FXML
    void backBtn() throws IOException {
        changeScene("/emris/control/readerControl/readerFxml/reader_.fxml");
    }

    @FXML
    void forwardBtn() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlSource));
        Scene scene = new Scene(loader.load());
        ReaderHandler readerTypeController = loader.getController();
        readerTypeController.setSession(session);
        readerTypeController.setStage(stage);
        readerTypeController.setName(name.getText());
        readerTypeController.setSurname(surname.getText());
        readerTypeController.setLibrary(libraryBox.getSelectionModel().getSelectedItem());
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup tg = new ToggleGroup();
        scholarR.setToggleGroup(tg);
        scientistR.setToggleGroup(tg);
        workerR.setToggleGroup(tg);
        studentR.setToggleGroup(tg);
        retiredR.setToggleGroup(tg);
        scholarR.setToggleGroup(tg);
        tg.selectedToggleProperty().addListener((ob, o, n) -> {

            RadioButton rb = (RadioButton) tg.getSelectedToggle();

            if (rb != null) {
                String s = rb.getText();
                switch (s) {
                    case ("школьник"):
                        this.fxmlSource = "/emris/control/readerControl/readerFxml/add_schoolar_student.fxml";
                        break;
                    case ("студент"):
                        this.fxmlSource = "/emris/control/readerControl/readerFxml/add_student.fxml";

                        break;
                    case ("ученый"):
                        this.fxmlSource = "/emris/control/readerControl/readerFxml/add_scientist.fxml";

                        break;
                    case ("пенсионер"):
                        this.fxmlSource = "/emris/control/readerControl/readerFxml/add_retired.fxml";

                        break;
                    case ("рабочий"):
                        this.fxmlSource = "/emris/control/readerControl/readerFxml/add_worker.fxml";
                        break;

                }

            }
        });


    }
}
