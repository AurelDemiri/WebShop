package domain.service;

import domain.db.*;
import domain.model.Product;
import domain.model.User;

import java.util.List;
import java.util.Properties;

public class ShopService {
    private UserDb userDb;
    private ProductDb productDb;

    public ShopService(Properties properties) {
        userDb = new UserDbSql(properties);
        productDb = new ProductDbSql(properties);
    }

    public User getUser(String userId) {
        return getUserDb().get(userId);
    }

    public List<User> getUsers() {
        return getUserDb().getAll();
    }

    public void addUser(User user) {
        getUserDb().add(user);
    }

    public void updateUser(User user) {
        getUserDb().update(user);
    }

    public void deleteUser(String userId) {
        getUserDb().delete(userId);
    }

    private UserDb getUserDb() {
        return userDb;
    }

    public Product getProduct(String productId) {
        return getProductDb().get(productId);
    }

    public List<Product> getProducts() {
        return getProductDb().getAll();
    }

    public void addProduct(Product product) {
        getProductDb().add(product);
    }

    public void updateProduct(Product product) {
        getProductDb().update(product);
    }

    public void deleteProduct(int id) {
        getProductDb().delete(id);
    }

    private ProductDb getProductDb() {
        return productDb;
    }

    public User getUserIfAuthenticated(String email, String password) {
        try {
            User user = getUserDb().getFromEmail(email);
            if (user.isPasswordCorrect(password)) {
                return user;
            } else {
                throw new DbException();
            }
        } catch (DbException e) {
            throw new IllegalArgumentException("Wrong email address or password");
        }
    }
}
