package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/mynewbase?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "golden";

    public static Connection getBaseConnaction() {
        Connection con = null;
        try {
             con = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    public static SessionFactory getSession() {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", url)
                .setProperty("hibernate.connection.username", user)
                .setProperty("hibernate.connection.password", password)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true")
                //.setProperty("hibernate.current_session_context_class", "thread")
               // .setProperty("hibernate.hbm2ddl.auto", "create")
                .addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}

