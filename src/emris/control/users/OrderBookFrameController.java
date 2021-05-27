package emris.control.users;

import emris.Constant;
import emris.control.ControllerHandler;
import emris.control.tableInfo.Book;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderBookFrameController extends ControllerHandler {
    @FXML
    private ChoiceBox<String> books;
    @FXML
    private Button select;

    private ArrayList<Book> auxList;

    public void initialize() {

        books.setOnMouseClicked(e -> {
            if (books.getItems().isEmpty()) {
                auxList = new ArrayList<>();
                ResultSet ret = null;
                try {
                    ret = session.executeQuery("SELECT" + Constant.adminName + ".\"book\".\"id\"," + Constant.adminName + ".\"book\".\"name\"," + Constant.adminName + ".\"book_type\".\"type\" as b_type from" + Constant.adminName + ".\"book\"," + Constant.adminName + ".\"book_type\" WHERE" + Constant.adminName + ".\"book\".\"type_id\"  =" + Constant.adminName + ".\"book_type\".\"id\"");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                while (true) {
                    try {
                        if (!ret.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    Book book = null;
                    try {
                        book = new Book(ret.getInt("id"), ret.getString("name"), ret.getString("b_type"));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    auxList.add(book);
                }

                ArrayList<String> choiceBookNames = new ArrayList<>();

                auxList.forEach(book -> choiceBookNames.add(book.getName()));

                books.setItems(FXCollections.observableArrayList(choiceBookNames));
            }
        });

        select.setOnAction(e -> {
            String name = books.getValue();

            if (name == null)
                return;//FIXME

            Book selected = auxList.stream().filter(book -> book.getName().equals(name)).findFirst().get();

            try {
                Statement st = session.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                int ret2 = st.executeUpdate("insert into " + Constant.adminName + ".\"orders\"(\"book_id\", \"reader_id\") values (" + selected.getId() + ", " + IdHandler.userId + ")");
                //FIXME SUCCESS
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
