package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Chou", "Yang", (byte) 31);
        userService.saveUser("Mike", "Lee", (byte) 30);
        userService.saveUser("Nick", "White", (byte) 22);
        userService.saveUser("Kate", "Wins", (byte) 28);
        userService.removeUserById(1);
        userService.getAllUsers();
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
    }
}

