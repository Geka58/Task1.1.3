package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `task1.1.4`.`user` " +
                    "(`id` BIGINT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL," +
                    "`last_name` VARCHAR(45) NOT NULL," +
                    "`age` TINYINT NOT NULL," +
                    "PRIMARY KEY (`id`))");
            System.out.println("Таблица создана.");
        } catch (SQLException e) {
            System.err.println("Не удалось создать таблицу.");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
            System.out.println("Таблица удалена.");
        } catch (SQLException e) {
            System.err.println("Не удалось удалить таблицу.");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String s = "INSERT INTO USER (name, last_name, age) VALUES(?,?,?)";
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

    @Override
    public void removeUserById(long id) {
        String s = "delete from user where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(s)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь c id = " + id + " удален.");
        } catch (SQLException e) {
            System.err.println("Не удалось удалить пользователя по id.");
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery("select * from user");
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("last_name"));
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

    @Override
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
