package emris.frames;

import emris.Session;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainFrame extends JFrame{
    private final Session session = new Session();
    MainFrame(int w,int h){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(w, h);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    session.closeConnection();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.exit(0);
            }
        });

        JButton createBtn = new JButton("Create Publications Table");
        createBtn.addActionListener(e -> {
            String dropTableQuery = "drop table publications";
            String publicationsTableQuery = "CREATE TABLE publications " + "(id INTEGER PRIMARY KEY, name char(20), publication_type INTEGER,hold_type INTEGER)";

            try {
                session.executeQuery(dropTableQuery);
                session.executeQuery(publicationsTableQuery);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        JTextField answer = new JTextField(" number of records in publications table: ");
        answer.setBounds(100,400,300,30);


        createBtn.setBounds(100, 100, 300, 30);
        this.add(createBtn);
        JButton addBtn = new JButton("add example data");
        addBtn.addActionListener(e ->{
            try {
                session.executeQuery("INSERT INTO publications " +
                        "VALUES (1, 'King Arthur', 1,1)");
                session.executeQuery("INSERT INTO publications " +
                        "VALUES (2, 'King Arthur 1', 1,1)");
                session.executeQuery("INSERT INTO publications " +
                        "VALUES (3, 'King Arthur 3', 1,1)");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        addBtn.setBounds(100, 200, 300, 30);
        JButton showBtn = new JButton("show count of records");
        showBtn.addActionListener(e ->{
            try {
               ResultSet res = session.executeQuery("select count(*) from publications");
               int count = -1;
                while (res.next()) {
                    count = res.getInt(1);
                }
                System.out.println(" number of records in publications table: " + count);
                answer.setText(answer.getText() + count);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        showBtn.setBounds(100, 300, 300, 30);



        this.add(showBtn);
        this.add(addBtn);
        this.add(answer);
        this.setLayout(null);




    }
    public void connect(String user,String pwd){
        session.createConnection(user, pwd);

    }

}
