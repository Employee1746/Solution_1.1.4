package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = null;
    private Transaction transaction;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        sessionFactory = Util.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(20), lastName VARCHAR(20), age INT)").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        sessionFactory = Util.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        sessionFactory = Util.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        sessionFactory = Util.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        sessionFactory = Util.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        sessionFactory = Util.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE users;").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
