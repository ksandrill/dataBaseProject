package emris.control.readerControl;

import emris.Constant;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class ScientistController extends ReaderHandler {
    @FXML
    TextField subject;
    @FXML
    TextField institution;

    @FXML
    void addBtn() throws IOException, SQLException {
        String deleteProcedure = "begin" + Constant.adminName + ".\"create_scientist\"(?,?,?,?,?); end;";
        CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
        cs.setString(1, name);
        cs.setString(2, surname);
        cs.setString(3, subject.getText());
        cs.setString(4, institution.getText());
        cs.setString(5, library);
        session.executeTrans(cs);

        createReaderUserDB();

        returnToReader();
    }


    @FXML
    void cancelBtn() throws IOException {
        returnToReader();

    }


}
