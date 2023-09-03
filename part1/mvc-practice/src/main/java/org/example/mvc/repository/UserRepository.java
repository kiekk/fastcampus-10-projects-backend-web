package org.example.mvc.repository;

import org.example.mvc.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private static Map<String, User> users = new HashMap<>();

    public static List<User> findAll() {
        return users.values().stream().toList();
    }

    public static void save(User user) {
        users.put(user.getUserId(), user);
    }

}
