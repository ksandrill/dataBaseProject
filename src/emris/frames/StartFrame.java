package emris.frames;

import emris.Session;

import javax.swing.*;
import java.util.Arrays;

public class StartFrame extends JFrame {

    public StartFrame(int w, int h) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(w, h);
        this.setTitle("dafaq");
        JTextField loginField = new JTextField();
        JPasswordField password = new JPasswordField();
        JButton button = new JButton("ok");
        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");
        loginField.setBounds(200, 200, 200, 30);
        password.setBounds(200, 240, 200, 30);
        button.setBounds(330, 290, 70, 30);
        loginLabel.setBounds(160, 200, 80, 30);
        passwordLabel.setBounds(135, 240, 80, 30);
        password.setEchoChar('*');
        this.add(loginField);
        this.add(password);
        this.add(button);
        this.add(loginLabel);
        this.add(passwordLabel);
        this.setLayout(null);
        this.setVisible(true);
        button.addActionListener(e -> {
            MainFrame mainFrame = new MainFrame(w, h);
            mainFrame.connect(loginField.getText(), String.valueOf(password.getPassword()));
            this.setVisible(false);
            mainFrame.setVisible(true);


        });

    }

}
