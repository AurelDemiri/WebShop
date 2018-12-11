package domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public int uniqueSize() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void remove(Product product) {
        if (product == null || !items.containsKey(product))
            throw new IllegalArgumentException("Item is not in cart");

        items.remove(product);
    }

    public void removeAmount(Product product, int amount) {
        if (product == null || !items.containsKey(product))
            throw new IllegalArgumentException("Item is not in cart");

        if (amount < 1) {
            throw new IllegalArgumentException("Amount has to be higher than 0");
        }

        int currentCount = items.get(product);

        if (amount >= currentCount) {
            items.remove(product);
        } else {
            items.put(product, currentCount - 1);
        }
    }

    public void add(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Can't add null to cart");

        items.put(product, items.containsKey(product) ? items.get(product) + 1 : 1);
    }

    public int getCount(Product product) {
        if (product == null || !items.containsKey(product))
            throw new IllegalArgumentException("Item is not in cart");

        return items.get(product);
    }

    public List<Product> getAll() {
        return new ArrayList<>(items.keySet());
    }

    public float calculateTotal() {
        double total = 0;
        for (Product item : items.keySet()) {
            total += items.get(item) * item.getPrice();
        }

        return (float) Math.round(total * 100f) / 100f;
    }
}
