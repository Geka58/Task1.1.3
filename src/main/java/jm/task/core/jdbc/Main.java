package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 24);
        userService.saveUser("Мария", "Петрова", (byte) 35);
        userService.saveUser("Светлана", "Сидорова", (byte) 22);
        userService.saveUser("Николай", "Светличный", (byte) 43);
        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

// реализуйте алгоритм здесь
    }
}
