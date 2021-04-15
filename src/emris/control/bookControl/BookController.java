package emris.control.bookControl;

import emris.control.ControllerHandler;
import emris.control.tableInfo.Book;
import emris.control.tableInfo.Librarian;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookController extends ControllerHandler implements Initializable {
    @FXML
    TableView<Book> tableView;

    private TableColumn<Librarian, Integer> idColumn;
    @FXML
    private TableColumn<Librarian, String> nameColumn;
    @FXML
    private TableColumn<Librarian, Boolean> onHandsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addBtn(){

    }

    @FXML
    void backBtn() throws IOException {
        changeScene("/emris/control/mainControl/main_frame.fxml");

    }

    @FXML
    void refreshBtn(){

    }
}
