package emris.control.readerControl;

import emris.Constant;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;

public class RetiredController extends ReaderHandler {
    @FXML
    DatePicker dataPicker;

    @FXML
    void addBtn() throws IOException, SQLException {
        String procedure = "begin" + Constant.adminName + ".\"create_retired\"(?,?,?,?); end;";
        CallableStatement cs = session.getConnection().prepareCall(procedure);
        cs.setString(1, name);
        cs.setString(2, surname);
        cs.setDate(3, Date.valueOf(dataPicker.getValue()));
        cs.setString(4, library);
        session.executeTrans(cs);

        createReaderUserDB();

        returnToReader();

    }

    @FXML
    void cancel() throws IOException {
        returnToReader();
    }

}
