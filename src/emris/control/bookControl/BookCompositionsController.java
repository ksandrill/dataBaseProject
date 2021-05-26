package emris.control.bookControl;

import emris.Constant;
import emris.control.ControllerHandler;
import emris.control.tableInfo.Composition;
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

public class BookCompositionsController extends ControllerHandler implements Initializable {


    @FXML
    private Label bookName;
    @FXML
    private TableColumn<Composition, Integer> idColumn;
    @FXML
    private TableColumn<Composition, String> nameColumn;
    @FXML
    private TableColumn<Composition, String> authorColumn;
    @FXML
    private TableColumn<Composition, String> genreColumn;

    @FXML
    private TableView<Composition> tableView;


    @FXML
    void addComposition() throws IOException {
        changeScene("/emris/control/bookControl/add_composition.fxml", handledStr, handledInt);


    }


    @FXML
    void backBtn() throws IOException {
        changeScene("/emris/control/bookControl/book_frame.fxml");
    }

    @FXML
    void placeBtn() throws IOException {
        changeScene("/emris/control/bookControl/placement_frame.fxml", handledStr, handledInt);

    }


    @FXML
    void refreshBtn() throws SQLException {
        bookName.setText(handledStr);
        ArrayList<Composition> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("select \"composition\".\"id\" as c_id,\"Name\" as comp_name ,\"Author\" as author ,\"book_id\", \"type_id\", \"composition_type\".\"id\", \"type\"  from" + Constant.adminName + ".\"composition\"," + Constant.adminName + ".\"composition_type\"\n" +
                "where (\"book_id\" =" + handledInt + "and \"type_id\" = \"composition_type\".\"id\")\n");
        while (ret.next()) {
            Composition composition = new Composition(ret.getInt("c_id"), ret.getString("comp_name"), ret.getString("type"), ret.getString("author"));
            auxList.add(composition);

        }
        tableView.refresh();

        tableView.setItems(FXCollections.observableArrayList(auxList));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));


    }
}
