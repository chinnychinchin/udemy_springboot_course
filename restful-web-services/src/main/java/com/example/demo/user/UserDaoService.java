package com.example.demo.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

// This is also known as the component or repository class (you'll often find the component/repository annotations)
// This class provides methods to 'talk' to the db, such as findAll(), findOne(), etc
@Component
public class UserDaoService {

    // To simulate the db
    private static List<User> users = new ArrayList<>();

    // A static Block
    static {
        users.add(new User(1,"John", new Date()));
        users.add(new User(2, "Jane", new Date()));
        users.add(new User(3, "Mark", new Date()));
    }
    private static Integer userCount;
    // Simulating the db end


    // methods to 'talk' to the db

    // find all
    public List<User> findAll() {
        return users;
    }

    // add or save
    public User save(User user) {

        userCount = users.size();
        System.out.println("Old user count: " + userCount);

        if (user.getId() == null) {

            // user.setId(userCount++); this code does not work because it is equivalent to user.setId(userCount) then userCount++
            user.setId(++userCount);
        }

        users.add(user);
        System.out.println("New user count: " + userCount);
        return user;
    }

    public User findOne(int id) {
        for (User user: users) {
            if (user.getId() == id) {
                return user;
            }

        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {

            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
