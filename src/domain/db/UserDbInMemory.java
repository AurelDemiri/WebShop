package domain.db;

import domain.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDbInMemory implements UserDb {
    private Map<Integer, User> users = new HashMap<>();

    public UserDbInMemory() {
        User administrator = new User("admin", "admin@ucll.be", "t", "Ad", "Ministrator");
        add(administrator);
    }

    @Override
    public User get(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new DbException("No id given");
        }
        if (!users.containsKey(userId)) {
            throw new DbException("User with id " + userId + " does not exist");
        }
        return users.get(userId);
    }

    @Override
    public User getFromEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new DbException("No email given");
        }

        User foundUser = users.values().stream().filter(x -> x.getEmail().equals(email)).findFirst().orElse(null);

        if (foundUser == null) {
            throw new DbException("User with email address \"" + email + "\" does not exist");
        }
        return foundUser;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(users.values());
    }

    @Override
    public void add(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }

        if (users.containsKey(user.getUserId())) {
            throw new DbException("User already exists");
        }

        if (users.values().stream().anyMatch(x -> x.getEmail().equals(user.getEmail()))) {
            throw new DbException("User with email address \"" + user.getEmail() + "\" already exists");
        }

        users.put(user.getUserId(), user);
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }
        if (!users.containsKey(user.getUserId())) {
            throw new DbException("No user found");
        }
        users.put(user.getUserId(), user);
    }

    @Override
    public void delete(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new DbException("No id given");
        }
        users.remove(userId);
    }

    @Override
    public int getNumberOfUsers() {
        return users.size();
    }
}
