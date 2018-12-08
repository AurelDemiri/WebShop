package domain.db;

import domain.model.DomainException;
import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDbSql implements ProductDb {

    private Properties properties;
    private String url;

    public ProductDbSql(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Product get(int id) {
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE productid = ?")
        ) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int productId = result.getInt("productid");
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");

                return new Product(productId, name, description, price);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        throw new DbException("Product with id " + id + " does not exist");
    }

    @Override
    public Product get(String id) {
        if (id == null || id.isEmpty()) {
            throw new DomainException("No id given");
        }
        try {
            return get(Integer.valueOf(id));
        } catch (NumberFormatException e) {
            throw new DomainException("No valid id given");
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                Statement statement = connection.createStatement()
        ) {
            ResultSet result = statement.executeQuery("SELECT * FROM products ORDER BY productid");
            while (result.next()) {
                int productId = result.getInt("productid");
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");

                Product product = new Product(productId, name, description, price);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return products;
    }

    @Override
    public void add(Product product) {
        if (product == null) {
            throw new DbException("No product given");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, description, price) VALUES (?,?,?)")
        ) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new DbException("No product given");
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("UPDATE products SET name = ?, description = ?, price = ? WHERE productid = ?")
        ) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE productid = ?")
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public int getNumberOfProducts() {
        // TODO: Can be done faster by rewriting query using COUNT(*)
        return getAll().size();
    }
}
