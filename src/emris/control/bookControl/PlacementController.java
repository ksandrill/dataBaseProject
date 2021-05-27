package emris.control.bookControl;

import emris.Constant;
import emris.control.ControllerHandler;
import emris.control.tableInfo.Placement;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlacementController extends ControllerHandler implements Initializable {
    @FXML
    TableView<Placement> tableView;
    @FXML
    private TableColumn<Placement, Integer> idColumn;
    @FXML
    private TableColumn<Placement, Integer> hallColumn;
    @FXML
    private TableColumn<Placement, Integer> shellColumn;
    @FXML
    private TableColumn<Placement, Integer> rackColumn;
    @FXML
    private TableColumn<Placement, String> statusColumn;

    @FXML
    private TableColumn<Placement, String> libraryColumn;

    @FXML
    private Label BookName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        hallColumn.setCellValueFactory(new PropertyValueFactory<>("hall"));
        shellColumn.setCellValueFactory(new PropertyValueFactory<>("shell"));
        rackColumn.setCellValueFactory(new PropertyValueFactory<>("rack"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        libraryColumn.setCellValueFactory(new PropertyValueFactory<>("library"));

    }

    @FXML
    void addBtn() throws IOException {
        changeScene("/emris/control/bookControl/add_placement_frame.fxml", handledStr, handledInt);

    }

    @FXML
    void backBtn() throws IOException {
        backToMain();
    }

    @FXML
    void backToCompositions() throws IOException {
        changeScene("/emris/control/bookControl/composition_frame.fxml", handledStr, handledInt);
    }

    @FXML
    void refreshBtn() throws SQLException {
        BookName.setText(handledStr);
        ArrayList<Placement> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("select \"book_placement\".\"id\" as \"b_id\", \"book_placement\".\"library_id\", \"book_placement\".\"hall_number\" as \"h_number\", \"book_placement\".\"rack\" as \"r_number\", \"book_placement\".\"shell\" as \"s_number\", \"book_placement\".\"status\" as \"stat\", \"book_placement\".\"book\", \"library\".\"id\", \"library\".\"name\" as \"lib_name\" from " + Constant.adminName + ".\"book_placement\"," + Constant.adminName + ".\"library\" where (\"library\".\"id\" = \"book_placement\".\"library_id\" and \"book_placement\".\"book\" = " + handledInt + ")");
        while (ret.next()) {
            Placement book = new Placement(ret.getInt("b_id"), ret.getInt("r_number"), ret.getInt("s_number"), ret.getInt("h_number"), ret.getString("stat"), ret.getString("lib_name"));
            auxList.add(book);

        }
        tableView.refresh();
        tableView.setItems(FXCollections.observableArrayList(auxList));

    }
}
