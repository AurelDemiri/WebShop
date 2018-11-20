package domain.db;

import domain.model.DomainException;
import domain.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDbInMemory implements ProductDb {
    private Map<Integer, Product> records = new HashMap<>();
    private int currentId = 0;

    public ProductDbInMemory() {
        Product rose = new Product("Rose", "Thorny plant", 2.25);
        add(rose);
    }

    @Override
    public Product get(int id) {
        if (id < 0) {
            throw new DbException("No valid id given");
        }

        if (!records.containsKey(id)) {
            throw new DbException("Product with id " + id + " does not exist");
        }

        return records.get(id);
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
        return new ArrayList<>(records.values());
    }

    @Override
    public void add(Product product) {
        if (product == null) {
            throw new DbException("No product given");
        }
        product.setProductId(currentId);
        currentId++;
        if (records.containsKey(product.getProductId())) {
            throw new DbException("Product already exists");
        }
        records.put(product.getProductId(), product);
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new DbException("No product given");
        }
        if (!records.containsKey(product.getProductId())) {
            throw new DbException("No product found");
        }
        records.put(product.getProductId(), product);
    }

    @Override
    public void delete(int id) {
        if (id < 0) {
            throw new DbException("No valid id given");
        }
        records.remove(id);
    }

    @Override
    public int getNumberOfProducts() {
        return records.size();
    }
}
