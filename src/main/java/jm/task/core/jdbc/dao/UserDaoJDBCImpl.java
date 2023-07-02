package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE `task1.1.4`.`user` " +
                    "(`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL," +
                    "`lastName` VARCHAR(45) NOT NULL," +
                    "`age` INT NOT NULL," +
                    "PRIMARY KEY (`id`))");
            System.out.println("Таблица создана.");
        } catch (SQLException e) {
            System.err.println("Не удалось создать таблицу.");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop table user");
            System.out.println("Таблица удалена.");
        } catch (SQLException e) {
            System.err.println("Не удалось удалить таблицу.");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String s = "INSERT INTO USER (name, lastName, age) VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(s)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println("Пользователь добавлен: " + name);
        } catch (SQLException e) {
            System.err.println("Не удалось добавить пользователя.");
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String s = "delete from user where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(s)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь c id = " + id + " удален.");
        } catch (SQLException e) {
            System.err.println("Не удалось удалить пользователя по id.");
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet res = statement.executeQuery("select * from user");
            while (res.next()){
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastName"));
                user.setAge(res.getByte("age"));
                users.add(user);
            }
            System.out.println("Список пользователей готов:");
        } catch (SQLException e) {
            System.err.println("Не удалось удалось создать список пользователей.");
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("delete from user");
            System.out.println("Таблица очищена.");
        } catch (SQLException e) {
            System.err.println("Не удалось очистить таблицу.");
            e.printStackTrace();
        }
    }
}
