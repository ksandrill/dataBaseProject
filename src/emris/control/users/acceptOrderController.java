package emris.control.users;

import emris.Constant;
import emris.control.ControllerHandler;
import emris.control.tableInfo.AccOrder;
import emris.control.tableInfo.Book;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class acceptOrderController extends ControllerHandler implements Initializable {
    @FXML
    TableView<AccOrder> tableView;
    @FXML
    private TableColumn<AccOrder, Integer> IdPlaceColumn;
    @FXML
    private TableColumn<AccOrder, Integer> idColumn;
    @FXML
    private TableColumn<AccOrder, String> bookNameColumn;
    @FXML
    private TableColumn<AccOrder, String> libraryNameColumn;

    @FXML
    private TableColumn<AccOrder, Integer> libIdColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("placeId"));
        libIdColumn.setCellValueFactory(new PropertyValueFactory<>("libId"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        libraryNameColumn.setCellValueFactory(new PropertyValueFactory<>("libName"));
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("выбрать");
        MenuItem mi2 = new MenuItem("отмена");
        cm.getItems().add(mi1);
        cm.getItems().add(mi2);
        mi1.setOnAction(event -> {
            AccOrder selectedAccOrder = tableView.getSelectionModel().getSelectedItem();

            try {
                acceptOrder(selectedAccOrder.getPlaceId());
                backBtn();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        });

        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if (t.getButton() == MouseButton.SECONDARY) {
                cm.show(tableView, t.getScreenX(), t.getScreenY());
            }
        });


    }

    void acceptOrder(int place) throws SQLException {
        System.out.println("dafaq");
        System.out.println(place);
        String deleteProcedure = "BEGIN  UPDATE " + Constant.adminName + ".\"orders\" " + "SET \"status\" = '" + Constant.accept + "'" + ", \"book_place\" = " + place + "WHERE  \"orders\".\"id\" = " + IdHandler.orderId + ";";
        deleteProcedure += "UPDATE " + Constant.adminName + ".\"book_placement\" " + "SET \"library_id\" = " + IdHandler.userLibId + ", \"status\" =  '" + Constant.onHandles + "' WHERE \"book_placement\".\"id\" = " + place + ";" + "end;";
        CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
        session.executeTrans(cs);

    }


    @FXML
    void backBtn() throws IOException {
        changeScene("/emris/control/users/lib_orders.fxml");
    }

    @FXML
    void refreshBtn() throws SQLException {
        ArrayList<AccOrder> auxList = new ArrayList<>();
        System.out.println(IdHandler.bookId);
        ResultSet ret = session.executeQuery("select \"book_placement\".\"library_id\" as \"lib_id\",\"book\".\"id\" as \"b_id\",\"book\".\"name\" as \"b_name\",\"book_placement\".\"id\" as \"place_id\",\"library\".\"name\" as \"lib_name\" from" + Constant.adminName + ".\"book\"," + Constant.adminName + ".\"book_placement\"," + Constant.adminName + ".\"library\" WHERE (\"book\".\"id\" = \"book_placement\".\"book\" and \"book\".\"id\" =" + IdHandler.bookId + "and \"book_placement\".\"library_id\" = \"library\".\"id\")");
        while (ret.next()) {
            AccOrder accOrder = new AccOrder(ret.getInt("b_id"), ret.getInt("place_id"), ret.getString("b_name"), ret.getString("lib_name"), ret.getInt("lib_id"));
            System.out.println(accOrder.getBookName());
            System.out.println(accOrder.getLibName());
            System.out.println(accOrder.getPlaceId());
            System.out.println(accOrder.getLibId());
            auxList.add(accOrder);
        }
        System.out.println(auxList.size());
        tableView.setItems(FXCollections.observableArrayList(auxList));
        tableView.refresh();


    }
}
