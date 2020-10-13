package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        User user = new User("Andrew", "Loginov", (byte) 25);
        User user2 = new User("Jane", "Smith", (byte) 29);
        User user3 = new User("Mickael", "Jackson", (byte) 50);
        User user4 = new User("Margarita", "Simonyan", (byte) 44);

        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> list = userService.getAllUsers();
        list.stream()
                .forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
