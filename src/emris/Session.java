package emris;

import java.sql.*;
import java.util.Locale;

public class Session {
    private Connection connection = null;
    private final String host = "localhost";
    private final int    port = 1521       ;
    private final String sid  = "XE"       ;
        ;
    private String url = String.format("jdbc:oracle:thin:@%s:%d:%s", host, port, sid);
    public Session(){

    }
    public void createConnection(String user,String pwd){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); //Проверяем наличие JDBC драйвера для работы с БД
            Locale def_locale = Locale.getDefault();
            Locale.setDefault(Locale.ENGLISH);
            this.connection = DriverManager.getConnection(url,user,pwd);//соединениесБД
            Locale.setDefault(def_locale);
            System.out.println("Соединение с СУБД выполнено.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }


    public void closeConnection() throws Exception {
        if(connection != null) {
            connection.close();
            System.out.println("Отключение от СУБД выполнено.");
        }

    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);

    }


}
