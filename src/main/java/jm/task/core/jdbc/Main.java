package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("Chou", "Yang", (byte) 31);
        userDao.saveUser("Mike", "Lee", (byte) 30);
        userDao.saveUser("Nick", "White", (byte) 22);
        userDao.saveUser("Kate", "Wins", (byte) 28);
        userDao.removeUserById(1);
        userDao.getAllUsers();
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
    }
}

