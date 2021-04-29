package emris.control.bookControl;

import emris.control.ControllerHandler;
import emris.control.tableInfo.Book;
import emris.control.tableInfo.Librarian;
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
import java.util.ResourceBundle;

public class BookController extends ControllerHandler implements Initializable {
    @FXML
    TableView<Book> tableView;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> nameColumn;
    @FXML
    private TableColumn<Book, String> bookType;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        bookType.setCellValueFactory(new PropertyValueFactory<>("type"));
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("удалить");
        MenuItem mi2 = new MenuItem("произведения...");
        MenuItem mi3 = new MenuItem("библиотеки...");
        MenuItem mi4 = new MenuItem("отмена");

        cm.getItems().add(mi1);
        cm.getItems().add(mi2);
        cm.getItems().add(mi3);
        cm.getItems().add(mi4);
        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if (t.getButton() == MouseButton.SECONDARY) {
                cm.show(tableView, t.getScreenX(), t.getScreenY());
            }
        });



    }

    @FXML
    void addBtn() throws IOException {
        changeScene("/emris/control/bookControl/add_book.fxml");

    }

    @FXML
    void backBtn() throws IOException {
        backToMain();
    }

    @FXML
    void refreshBtn() throws SQLException {
        ArrayList<Book> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("SELECT \"book\".\"id\", \"book\".\"name\", \"book_type\".\"type\" as b_type from \"book\", \"book_type\" WHERE \"book\".\"type_id\"  = \"book_type\".\"id\"");
        while (ret.next()) {
            Book book = new Book(ret.getInt("id"), ret.getString("name"), ret.getString("b_type"));
            ////System.out.println(reader.getId() + reader.getName() +  reader.getSurname() +  reader.getRoleType());
            auxList.add(book);

        }
        tableView.refresh();

        tableView.setItems(FXCollections.observableArrayList(auxList));

    }
}
