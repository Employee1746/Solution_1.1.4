package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users " +
            "(Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";
    private final static String DROP_TABLE = "DROP TABLE IF EXISTS users";

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(CREATE_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка создания таблицы");
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(DROP_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления таблицы");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("Ошибка добавления пользователя");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления пользователя");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE users;").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления пользователей");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
















//        List<User> userList = null;
//        Transaction transaction = null;
//        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            userList = session.createQuery("from User").list();
//            return userList;
//        } catch (Exception e) {
//            System.out.println("Ошибка получения списка пользователей");
//        }
//        return userList;