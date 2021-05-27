package emris.control.users;

import emris.Constant;
import emris.control.ControllerHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class execOrderController extends ControllerHandler {
    @FXML
    void giveOrder() throws IOException {
        if (IdHandler.status == null) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("внимание");
            a.setHeaderText("заказ сначала нужно одобрить !");
            a.showAndWait();
        } else if (!IdHandler.status.equals(Constant.accept)) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("внимание");
            a.setHeaderText("уже " + IdHandler.status);
            a.showAndWait();
        } else {
            changeScene("/emris/control/users/accept_order.fxml");

        }
        changeScene("/emris/control/users/give_order.fxml");

    }

    @FXML
    void declineOrder() throws SQLException {
        if (IdHandler.status == null) {
            String deleteProcedure = "UPDATE " + Constant.adminName + ".\"orders\" " + "SET \"status\" = '" + Constant.decline + "' WHERE  \"orders\".\"id\" = " + IdHandler.orderId;
            CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
            session.executeTrans(cs);
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("внимание");
            a.setHeaderText("уже " + IdHandler.status);
            a.showAndWait();
        }


    }

    @FXML
    void acceptOrder() throws IOException {
        if (IdHandler.status == null) {
            changeScene("/emris/control/users/accept_order.fxml");
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("внимание");
            a.setHeaderText("уже " + IdHandler.status);
            a.showAndWait();
        }


    }

    @FXML
    void backBtn() throws IOException {
        changeScene("/emris/control/users/lib_orders.fxml");
    }
}
