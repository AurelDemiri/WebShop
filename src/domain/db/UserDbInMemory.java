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
