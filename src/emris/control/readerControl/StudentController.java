package emris.control.readerControl;

import emris.Constant;
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
    addBtn() throws IOException, SQLException {
        String procedure = "begin" + Constant.adminName + ".\"create_student\"(?,?,?,?,?,?,?); end;";
        CallableStatement cs = session.getConnection().prepareCall(procedure);
        cs.setString(1, name);
        cs.setString(2, surname);
        cs.setString(3, unniver.getText());
        cs.setString(4, department.getText());
        cs.setString(5, course.getText());
        cs.setString(6, group.getText());
        cs.setString(7, library);
        session.executeTrans(cs);

        returnToReader();
    }


}
