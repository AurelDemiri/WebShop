package domain.db;

import domain.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDbSql implements UserDb {

    private Properties properties;
    private String url;

    public UserDbSql(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public User get(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new DbException("No id given");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE userid = ?")
        ) {
            try {
                statement.setInt(1, Integer.parseInt(userId));
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("User id is not a valid number");
            }

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");

                return new User(userId, email, password, firstName, lastName);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        throw new DbException("User with id " + userId + " does not exist");
    }

    @Override
    public User getFromEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new DbException("No email address given");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")
        ) {
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String userId = String.valueOf(result.getInt("userid"));
                String password = result.getString("password");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");

                return new User(userId, email, password, firstName, lastName);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        throw new DbException("User with email address \"" + email + "\" does not exist");
    }

    @Override
    public List<User> getAll() {
        List<User> people = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                Statement statement = connection.createStatement()
        ) {
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            while (result.next()) {
                String userId = String.valueOf(result.getInt("userid"));
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");

                User user = new User(userId, email, password, firstName, lastName);
                people.add(user);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return people;
    }

    @Override
    public void add(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }

        // TODO: OPTIMIZE!
        if (getAll().stream().anyMatch((p) -> p.getEmail().equals(user.getEmail()))) {
            throw new DbException("User with email address \"" + user.getEmail() + "\" already exists");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, password, firstname, lastname) VALUES (?,?,?,?)")
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("UPDATE users SET email = ?, password = ?, firstname = ?, lastname = ? WHERE userid = ?")
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void delete(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new DbException("No id given");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE userid = ?")
        ) {
            try {
                statement.setInt(1, Integer.parseInt(userId));
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("User id is not a valid number");
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public int getNumberOfUsers() {
        // TODO: Can be done faster by rewriting query using COUNT(*)
        return getAll().size();
    }
}
