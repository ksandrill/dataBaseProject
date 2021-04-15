package emris.control.readerControl;

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
    void addBtn() throws IOException {
        String deleteProcedure = "begin \"create_retired\"(?,?,?,?); end;";
        try {
            CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
            cs.setString(1, name);
            cs.setString(2, surname);
            cs.setDate(3, Date.valueOf(dataPicker.getValue()));
            cs.setString(4, "Шукшинская");
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
