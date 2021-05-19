package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    static String createTable = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";
    static String dropTable = "DROP TABLE IF EXISTS users";
    static String deleteById = "DELETE FROM users WHERE Id = ";
    static String clearAllUsers = "TRUNCATE users;";

    public void createUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTable);
            System.out.println("Table has been created!");
        } catch (SQLException e) {
            System.out.println("Table creating error");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTable);
            System.out.println("Table has been deleted!");
        } catch (SQLException e) {
            System.out.println("Table deleting error");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMySQLConnection()) {
            String insert = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, String.valueOf(age));
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Saving error");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteById + id);
            System.out.println("User with id = " + id + " has been removed");
        } catch (SQLException e) {
            System.out.println("User removing error");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getInt(4);
                list.add(new User(name, lastName, (byte) age));
            }
        } catch (SQLException e) {
            System.out.println("User getting error");
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(clearAllUsers);
            System.out.println("Users has been cleaned");
        } catch (SQLException e) {
            System.out.println("Table cleaning error");
        }
    }
}
