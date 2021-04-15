package emris.control.librarianControl;

import emris.control.ControllerHandler;
import emris.control.tableInfo.Librarian;
import emris.control.tableInfo.Reader;
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

public class LibrarianController extends ControllerHandler implements Initializable {
    @FXML
    private TableView<Librarian> tableView;
    @FXML
    private TableColumn<Librarian, Integer> idColumn;
    @FXML
    private TableColumn<Librarian, String> nameColumn;
    @FXML
    private TableColumn<Librarian, String> surnameColumn;
    @FXML
    private TableColumn<Librarian, String> libraryColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        libraryColumn.setCellValueFactory(new PropertyValueFactory<>("library"));
//        ContextMenu cm = new ContextMenu();
//        MenuItem mi1 = new MenuItem("удалить");
//        mi1.setOnAction(event -> {
//            Reader selectedReader = tableView.getSelectionModel().getSelectedItem();
//            String deleteProcedure = "begin \"delete_reader\"(?); end;";
//            try {
//                CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
//                cs.setString(1, Integer.toString(selectedReader.getId()));
//                cs.executeUpdate();
//
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//            try {
//                refresh_table();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//
//        });
//        MenuItem mi2 = new MenuItem("показать лич инфо");
//        MenuItem mi3 = new MenuItem("показать книж инфо");
//        MenuItem mi4 = new MenuItem("отмена");
//
//        cm.getItems().add(mi1);
//        cm.getItems().add(mi2);
//        cm.getItems().add(mi3);
//        cm.getItems().add(mi4);
//
//        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
//            if (t.getButton() == MouseButton.SECONDARY) {
//                cm.show(tableView, t.getScreenX(), t.getScreenY());
//            }
//        });

    }

    @FXML
    void addLibrarian() {

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
        ArrayList<Librarian> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("select \"librarian\".\"id\", \"librarian\".\"name\",\"librarian\".\"surname\",\"library\".\"name\" as lib_name from \"librarian\",\"library\"\n" +
                "WHERE \"librarian\".\"library_id\" = \"library\".\"id\"");
        while (ret.next()) {
            auxList.add(new Librarian(ret.getInt("id"), ret.getString("name"), ret.getString("surname"), ret.getString("lib_name")));

        }
        tableView.refresh();

        tableView.setItems(FXCollections.observableArrayList(auxList));


    }
}
