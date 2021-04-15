package emris.control.readerControl;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class StudentController extends ReaderHandler {

    @FXML
    TextField unniver;
    @FXML
    TextField department;
    @FXML
    TextField course;
    @FXML
    TextField group;



    @FXML
    void
    addBtn() throws IOException {
        String deleteProcedure = "begin \"create_student\"(?,?,?,?,?,?,?); end;";
        try {
            CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
            cs.setString(1, name);
            cs.setString(2, surname);
            cs.setString(3, unniver.getText());
            cs.setString(4, department.getText());
            cs.setString(5, course.getText());
            cs.setString(6, group.getText());
            cs.setString(7, "Шукшинская");
            cs.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        returnToReader();
    }


}
