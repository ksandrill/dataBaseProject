package emris.control.bookControl;

import emris.Constant;
import emris.control.ControllerHandler;
import emris.control.tableInfo.Binfo;
import emris.control.tableInfo.Book;
import emris.control.users.IdHandler;
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
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class BookInfo extends ControllerHandler implements Initializable {
    @FXML
    TableView<Binfo> tableView;
    @FXML
    TableColumn<Binfo, Integer> idColumn;
    @FXML
    TableColumn<Binfo, Integer> librarianIdColumn;
    @FXML
    TableColumn<Binfo, Integer> placeIdColumn;
    @FXML
    TableColumn<Binfo, String> bookNameColumn;
    @FXML
    TableColumn<Binfo, String> librarianNameColumn;
    @FXML
    TableColumn<Binfo, Date> whenTakeColumn;
    @FXML
    TableColumn<Binfo, Date> whenShouldReturnColumn;
    @FXML
    TableColumn<Binfo, Date> whenReturnColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        librarianIdColumn.setCellValueFactory(new PropertyValueFactory<>("librarian_id"));
        placeIdColumn.setCellValueFactory(new PropertyValueFactory<>("place_id"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        librarianNameColumn.setCellValueFactory(new PropertyValueFactory<>("librarian"));
        whenTakeColumn.setCellValueFactory(new PropertyValueFactory<>("whenTake"));
        whenShouldReturnColumn.setCellValueFactory(new PropertyValueFactory<>("whenShouldReturn"));
        whenReturnColumn.setCellValueFactory(new PropertyValueFactory<>("whenReturn"));
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("сдать");
        mi1.setOnAction(event -> {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String deleteProcedure = "UPDATE " + Constant.adminName + ".\"books_on_hands\" " + "SET \"when_return\" = " + "?" + " WHERE  \"id\" = " + tableView.getSelectionModel().getSelectedItem().getId();
            try {
                CallableStatement cs = session.getConnection().prepareCall(deleteProcedure);
                cs.setDate(1, date);
                session.executeTrans(cs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                refreshBtn();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        MenuItem mi2 = new MenuItem("отмена");
        cm.getItems().add(mi1);
        cm.getItems().add(mi2);
        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if (t.getButton() == MouseButton.SECONDARY) {
                cm.show(tableView, t.getScreenX(), t.getScreenY());
            }
        });


    }

    @FXML
    public void refreshBtn() throws SQLException {
        ArrayList<Binfo> auxList = new ArrayList<>();
        ResultSet ret = session.executeQuery("select \"books_on_hands\".\"id\" as \"boh_id\",\"who_give\",\"book_placement_id\",\"when_take\", \"when_should_return\", \"when_return\", \"librarian\".\"name\" as \"l_name\",\"librarian\".\"surname\" as \"l_surname\", \"book_placement\".\"book\" as \"b_id\" from" + Constant.adminName + ".\"books_on_hands\"," + Constant.adminName + ".\"librarian\"," + Constant.adminName + ".\"book_placement\" where  (\"librarian\".\"id\" = \"who_give\"  and \"book_placement_id\" = \"book_placement\".\"id\")");
        while (ret.next()) {
            int bookId = ret.getInt("b_id");
            ResultSet retName = session.executeQuery("select \"book\".\"name\" as \"book_name\" from " + Constant.adminName + ".\"book\" where \"book\".\"id\" = " + bookId);
            retName.next();
            String bookName = retName.getString("book_name");
            Binfo dafaq = new Binfo(ret.getInt("boh_id"), ret.getInt("who_give"), ret.getInt("book_placement_id"), ret.getDate("when_take"), ret.getDate("when_should_return"), ret.getDate("when_return"), ret.getString("l_name") + " " + ret.getString("l_surname"), bookName);
            auxList.add(dafaq);

        }
        tableView.refresh();

        tableView.setItems(FXCollections.observableArrayList(auxList));


    }

    @FXML
    public void backBtn() throws IOException {
        changeScene(handledStr);

    }
}
