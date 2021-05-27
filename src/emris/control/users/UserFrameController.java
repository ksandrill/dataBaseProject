package emris.control.users;

import emris.control.ControllerHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserFrameController extends ControllerHandler
{
    @FXML
    private Button loadOrderFrame;
    @FXML
    private Button loadUserInfoFrame;
    @FXML
    private Button loadBookInfoFrame;

    public void initialize()
    {
        loadOrderFrame.setOnAction(e -> {
            try {
                changeScene("/emris/control/users/orderBookFrame.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
