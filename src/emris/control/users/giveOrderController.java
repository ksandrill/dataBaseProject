package emris.control.users;

import emris.Constant;
import emris.control.ControllerHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class giveOrderController extends ControllerHandler {
    @FXML
    DatePicker dataPicker;

    @FXML
    void addBtn() throws IOException, SQLException {
        ResultSet ret_lib_id = session.executeQuery("select \"orders\".\"book_place\" as \"b_place\" from" + Constant.adminName + ".\"orders\" WHERE \"orders\".\"id\" =" + IdHandler.orderId);
        ret_lib_id.next();
        int place_id = ret_lib_id.getInt("b_place");
        String insertStr = "BEGIN INSERT INTO" + Constant.adminName + ".\"books_on_hands\"(\"reader_id\",\"book_placement_id\",\"when_take\",\"when_should_return\",\"who_give\") VALUES (?,?,?,?,?);";
        insertStr += "UPDATE " + Constant.adminName + ".\"orders\" " + "SET \"status\" = '" + Constant.gived + "' WHERE  \"orders\".\"id\" = " + IdHandler.orderId + "; " + " end;";
        CallableStatement cs = session.getConnection().prepareCall(insertStr);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        cs.setInt(1, IdHandler.readerId);
        cs.setInt(2, place_id);
        cs.setDate(3, date);
        cs.setDate(4, Date.valueOf(dataPicker.getValue()));
        cs.setInt(5, Integer.parseInt(IdHandler.userId));
        session.executeTrans(cs);
        cancel();


    }

    @FXML
    void cancel() throws IOException {
        changeScene("/emris/control/users/lib_orders.fxml");
    }

}
