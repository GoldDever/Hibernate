package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.FROM;

public class UserDaoJDBCImpl implements UserDao {
    SessionFactory sessionFactory;
    Transaction transaction = null;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String str = "CREATE TABLE IF NOT EXISTS mytable(id BIGINT NOT NULL AUTO_INCREMENT, name varchar(255), lastName varchar(255)," +
                "age TINYINT(10), PRIMARY KEY(id))";
        try {
            sessionFactory = Util.getSession();
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(str).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    public void dropUsersTable() {
        String str = "DROP TABLE IF EXISTS mytable";
        try {
            sessionFactory = Util.getSession();
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(str).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
       try {
           sessionFactory = Util.getSession();
           Session session = sessionFactory.openSession();
           transaction = session.beginTransaction();
           session.save(user);
           transaction.commit();
           System.out.println("User saved and commited");
       } catch (HibernateException e) {
           if(transaction != null) transaction.rollback();
           e.printStackTrace();
       } finally {
           sessionFactory.close();
       }
    }

    public void removeUserById(long id) {
        try {
            sessionFactory = Util.getSession();
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
       try {
           sessionFactory = Util.getSession();
           Session session = sessionFactory.openSession();
           transaction = session.beginTransaction();
           list = session.createQuery("FROM User").list();
           //list = session.createCriteria(User.class).list();
           transaction.commit();
       } catch (HibernateException e) {
           if(transaction != null) transaction.rollback();
           e.printStackTrace();
       } finally {
           sessionFactory.close();
       }
        return list;
    }

    public void cleanUsersTable() {
        String str = "DELETE FROM User";
        try {
            sessionFactory = Util.getSession();
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery(str).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
