package emris.control.readerControl;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;


public class WorkerController extends ReaderHandler {


    public TextField getWorkplaceField() {
        return workplaceField;
    }

    public void setWorkplaceField(TextField workplaceField) {
        this.workplaceField = workplaceField;
    }


    @FXML
    TextField workplaceField;


    @FXML
    void addBtn() throws IOException {
        String deleteProcedure = "begin \"create_worker\"(?,?,?,?); end;";
        try {
            CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
            cs.setString(1, name);
            cs.setString(2, surname);
            cs.setString(3, workplaceField.getText());
            cs.setString(4, library);
            cs.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        returnToReader();


    }

//
}
