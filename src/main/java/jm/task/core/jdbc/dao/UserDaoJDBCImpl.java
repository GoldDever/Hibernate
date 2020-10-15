package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
Connection con;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
       String str ="CREATE TABLE IF NOT EXISTS SuperTable(id INT NOT NULL AUTO_INCREMENT," +
                                   "name VARCHAR(255) NOT NULL, lastName varchar (255) NOT NULL, age TINYINT(10), PRIMARY KEY(id))";

        try (Connection connection = Util.getBaseConnaction();
              Statement statement = connection.createStatement()){
            con = connection;
            connection.setAutoCommit(false);
            statement.executeUpdate(str);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String str ="DROP TABLE IF EXISTS SuperTable";
        try (Connection connection = Util.getBaseConnaction(); Statement statement = connection.createStatement()){
            con = connection;
            connection.setAutoCommit(false);
            statement.executeUpdate(str);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String str = "INSERT INTO SuperTable(name, lastName, age) VALUES('" + name + "','" + lastName + "'," + age + ")";
        try (Connection connection = Util.getBaseConnaction(); Statement statement = connection.createStatement()){
            con = connection;
            connection.setAutoCommit(false);
            statement.executeUpdate(str);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        System.out.printf("User с именем – %s добавлен в базу данных" + "\n", name);
    }

    public void removeUserById(long id) {
        String str = "DELETE FROM SuperTable WHERE id = " + id;
        try (Connection connection = Util.getBaseConnaction(); Statement statement = connection.createStatement()){
            con = connection;
            connection.setAutoCommit(false);
            statement.executeUpdate(str);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = Util.getBaseConnaction(); Statement statement = connection.createStatement()){
            con = connection;
            connection.setAutoCommit(false);
            resultSet = statement.executeQuery("select * from SuperTable");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                User user = new User(name, surname, (byte) age);
                list.add(user);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String str = "TRUNCATE TABLE SuperTable";
        try (Connection connection = Util.getBaseConnaction(); Statement statement = connection.createStatement()){
            con = connection;
            connection.setAutoCommit(false);
            statement.executeUpdate(str);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }
}
