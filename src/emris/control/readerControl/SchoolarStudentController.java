package emris.control.readerControl;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class SchoolarStudentController extends ReaderHandler {
    @FXML
    TextField school;
    @FXML
    TextField letter;
    @FXML
    TextField classNumber;

    @FXML
    void addBtn() throws IOException {
        String deleteProcedure = "begin \"create_school_student\"(?,?,?,?,?,?); end;";
        try {
            CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
            cs.setString(1, name);
            cs.setString(2, surname);
            cs.setString(3, classNumber.getText());
            cs.setString(4, letter.getText());
            cs.setString(5, school.getText());
            cs.setString(6, library);
            cs.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        returnToReader();
    }
    @FXML
    void cancel() throws IOException {
        returnToReader();
    }

}





