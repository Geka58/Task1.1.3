package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DRIVE = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/task1.1.4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DRIVE);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Подключено к БД.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка подключения к БД.");
        }
        return connection;
    }  // реализуйте настройку соеденения с БД
}
