package domain.db;

import domain.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDbSql implements PersonDb {

    private Properties properties;
    private String url;

    public PersonDbSql(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Person get(String personId) {
        if (personId == null || personId.isEmpty()) {
            throw new DbException("No id given");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE userid = ?")
        ) {
            statement.setString(1, personId);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String userId = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");

                return new Person(userId, email, password, firstName, lastName);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        throw new DbException("Person with id " + personId + " does not exist");
    }

    @Override
    public List<Person> getAll() {
        List<Person> people = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                Statement statement = connection.createStatement()
        ) {
            ResultSet result = statement.executeQuery("SELECT * FROM person");
            while (result.next()) {
                String userId = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");

                Person person = new Person(userId, email, password, firstName, lastName);
                people.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return people;
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("No person given");
        }

        // TODO: OPTIMIZE!
        if (getAll().stream().anyMatch((p) -> p.getUserId().equals(person.getUserId()))) {
            throw new DbException("User already exists");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO person (userid, email, password, firstname, lastname) VALUES (?,?,?,?,?)")
        ) {
            statement.setString(1, person.getUserId());
            statement.setString(2, person.getEmail());
            statement.setString(3, person.getPassword());
            statement.setString(4, person.getFirstName());
            statement.setString(5, person.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void update(Person person) {
        if (person == null) {
            throw new DbException("No person given");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("UPDATE person SET email = ?, password = ?, firstname = ?, lastname = ? WHERE userid = ?")
        ) {
            statement.setString(1, person.getEmail());
            statement.setString(2, person.getPassword());
            statement.setString(3, person.getFirstName());
            statement.setString(4, person.getLastName());
            statement.setString(5, person.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void delete(String personId) {
        if (personId == null || personId.isEmpty()) {
            throw new DbException("No id given");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE userid = ?")
        ) {
            statement.setString(1, personId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public int getNumberOfPersons() {
        // TODO: Can be done faster by rewriting query using COUNT(*)
        return getAll().size();
    }
}
