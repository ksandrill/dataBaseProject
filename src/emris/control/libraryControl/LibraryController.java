package emris.control.libraryControl;

import emris.control.ControllerHandler;
import emris.control.tableInfo.Librarian;
import emris.control.tableInfo.Library;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LibraryController extends ControllerHandler implements Initializable {
    @FXML
    private TableView<Library> tableView;
    @FXML
    private TableColumn<Library, Integer> idColumn;
    @FXML
    private TableColumn<Library, String> nameColumn;
    @FXML
    private TableColumn<Library, String> addrColumn;


    @FXML
    void addBtn() throws IOException {
        changeScene("/emris/control/libraryControl/add_library.fxml");

    }

    @FXML
    void backBtn() throws IOException {
        changeScene("/emris/control/mainControl/main_frame.fxml");

    }

    @FXML
    void refreshBtn() throws SQLException {
        refreshTable();

    }

    void refreshTable() throws SQLException {
        ArrayList<Library> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("select \"library\".\"id\", \"library\".\"name\",\"library\".\"addr\" from \"library\"\n");
        while (ret.next()) {
            auxList.add(new Library(ret.getInt("id"), ret.getString("name"), ret.getString("addr")));

        }
        tableView.refresh();

        tableView.setItems(FXCollections.observableArrayList(auxList));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addrColumn.setCellValueFactory(new PropertyValueFactory<>("addr"));
    }

}
