//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users " +
//            "(Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";
//    private final static String DROP_TABLE = "DROP TABLE IF EXISTS users";
//    private final static String DELETE_BY_ID = "DELETE FROM users WHERE Id = ";
//    private final static String CLEAR_ALL_USERS = "TRUNCATE users;";
//    private final static String INSERT_USER = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";
//    private final static String GET_ALL_USERS = "SELECT * FROM users";
//
//    public UserDaoJDBCImpl() {
//    }
//
//    public void createUsersTable() {
//        try (Statement statement = Util.getMySQLConnection().createStatement()) {
//            statement.executeUpdate(CREATE_TABLE);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void dropUsersTable() {
//        try (Statement statement = Util.getMySQLConnection().createStatement()) {
//            statement.executeUpdate(DROP_TABLE);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        try (Connection connection = Util.getMySQLConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setString(3, String.valueOf(age));
//            preparedStatement.executeUpdate();
//            System.out.println("User с именем – " + name + " добавлен в базу данных");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removeUserById(long id) {
//        try (Statement statement = Util.getMySQLConnection().createStatement()) {
//            statement.executeUpdate(DELETE_BY_ID + id);
//            System.out.println("User with id = " + id + " has been removed");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> list = new ArrayList<>();
//        try (Statement statement = Util.getMySQLConnection().createStatement();
//             ResultSet resultSet = statement.executeQuery(GET_ALL_USERS)) {
//            while (resultSet.next()) {
//                String name = resultSet.getString(2);
//                String lastName = resultSet.getString(3);
//                int age = resultSet.getInt(4);
//                list.add(new User(name, lastName, (byte) age));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public void cleanUsersTable() {
//        try (Statement statement = Util.getMySQLConnection().createStatement()) {
//            statement.executeUpdate(CLEAR_ALL_USERS);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}