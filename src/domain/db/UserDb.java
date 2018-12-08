package domain.db;

import domain.model.User;

import java.util.List;

public interface UserDb {
    User get(String userId);

    User getFromEmail(String email);

    List<User> getAll();

    void add(User user);

    void update(User user);

    void delete(String userId);

    int getNumberOfUsers();
}
