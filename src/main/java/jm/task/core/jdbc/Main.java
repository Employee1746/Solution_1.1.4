package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Dark", "Side", (byte) 31);
        userService.saveUser("Sara", "Connor", (byte) 30);
        userService.saveUser("Corban", "Dallas", (byte) 22);
        userService.saveUser("Sub", "Zero", (byte) 28);
//        userService.removeUserById(2);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();

    }
}

