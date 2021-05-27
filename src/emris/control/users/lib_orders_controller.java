package emris.control.users;

import emris.Constant;
import emris.control.ControllerHandler;
import emris.control.tableInfo.LibOrder;
import emris.control.tableInfo.Reader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class lib_orders_controller extends ControllerHandler implements Initializable {
    @FXML
    TableView<LibOrder> tableView;
    @FXML
    private TableColumn<LibOrder, Integer> idColumn;
    @FXML
    private TableColumn<LibOrder, Integer> readerIdColumn;
    @FXML
    private TableColumn<LibOrder, Integer> bookIdColumn;
    @FXML
    private TableColumn<LibOrder, String> readerNameColumn;
    @FXML
    private TableColumn<LibOrder, String> bookNameColumn;
    @FXML
    private TableColumn<LibOrder, String> statusColumn;

    @FXML
    void backBtn() throws IOException {
        backToMain();
    }

    @FXML
    void refreshBtn() throws SQLException {
        ArrayList<LibOrder> auxList = new ArrayList<>();
//        ResultSet ret = session.executeQuery("select  \"orders\".\"status\" as \"stat\",\"orders\".\"id\" as \"o_id\", \"orders\".\"book_id\" as \"b_id\", \"orders\".\"reader_id\" as \"r_id\", \"book\".\"name\" as \"b_name\", \"reader\".\"name\" as \"r_name\", \"reader\".\"surname\" as \"r_surname\" from " + Constant.adminName + ".\"orders\"," + Constant.adminName + ".\"reader\"," + Constant.adminName + ".\"book\"\n" +
//                "WHERE (\"orders\".\"book_id\" = \"book\".\"id\" and \"orders\".\"reader_id\" = \"reader\".\"id\")");
        System.out.println(IdHandler.userId);
        ResultSet ret_lib_id = session.executeQuery("select \"librarian\".\"id\",\"librarian\".\"library_id\" as lib_id from" + Constant.adminName + ".\"librarian\" WHERE \"librarian\".\"id\" =" + IdHandler.userId);
        ret_lib_id.next();
        int libId = ret_lib_id.getInt("lib_id");
        IdHandler.userLibId = libId;
        ResultSet ret = session.executeQuery("select  \"orders\".\"status\" as \"stat\",\"orders\".\"id\" as \"o_id\", \"orders\".\"book_id\" as \"b_id\", \"orders\".\"reader_id\" as \"r_id\", \"book\".\"name\" as \"b_name\", \"reader\".\"name\" as \"r_name\", \"reader\".\"surname\" as \"r_surname\", \"reader\".\"library_id\"  from " + Constant.adminName + ".\"orders\"," + Constant.adminName + ".\"reader\"," + Constant.adminName + ".\"book\"\n" +
                "WHERE (\"orders\".\"book_id\" = \"book\".\"id\" and \"orders\".\"reader_id\" = \"reader\".\"id\" and \"reader\".\"library_id\" = " + libId + ")");
        while (ret.next()) {
            auxList.add(new LibOrder(ret.getInt("o_id"), ret.getInt("b_id"), ret.getInt("r_id"), ret.getString("r_name") + " " + ret.getString("r_surname"), ret.getString("b_name"), ret.getString("stat")));

        }
        tableView.refresh();
        tableView.setItems(FXCollections.observableArrayList(auxList));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        readerIdColumn.setCellValueFactory(new PropertyValueFactory<>("readerId"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        readerNameColumn.setCellValueFactory(new PropertyValueFactory<>("reader"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("book"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("обработать заказ");
        MenuItem mi2 = new MenuItem("показать инфо о читателе");
        MenuItem mi4 = new MenuItem("отмена");
        mi1.setOnAction(event -> {
            try {
                IdHandler.readerId = tableView.getSelectionModel().getSelectedItem().getReaderId();
                IdHandler.bookId = tableView.getSelectionModel().getSelectedItem().getBookId();
                IdHandler.orderId = tableView.getSelectionModel().getSelectedItem().getId();
                IdHandler.status = tableView.getSelectionModel().getSelectedItem().getStatus();
                changeScene("/emris/control/users/exec_order.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }


        });


        mi2.setOnAction(event -> {
            try {
                changeScene("/emris/control/bookControl/book_info_frame.fxml", "/emris/control/users/lib_orders.fxml", tableView.getSelectionModel().getSelectedItem().getReaderId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cm.getItems().add(mi1);
        cm.getItems().add(mi2);
        cm.getItems().add(mi4);


        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if (t.getButton() == MouseButton.SECONDARY) {
                cm.show(tableView, t.getScreenX(), t.getScreenY());
            }
        });


    }
}
