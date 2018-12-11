package domain.model;

import java.util.Objects;

public class Product {
    private int productId;
    private String name;
    private String description;
    private double price;

    public Product(int productId, String name, String description, double price) {
        setProductId(productId);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public Product(int productId, String name, String description, String price) {
        setProductId(productId);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public Product(String name, String description, double price) {
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public Product(String name, String description, String price) {
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new DomainException("No name given");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new DomainException("No description given");
        }

        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new DomainException("The price \"" + price + "\" is not valid");
        }
        this.price = price;
    }

    public void setPrice(String price) {
        if (price == null || price.isEmpty()) {
            throw new DomainException("No price given");
        }
        try {
            setPrice(Double.valueOf(price));
        } catch (NumberFormatException e) {
            throw new DomainException("Give a valid price");
        }
    }

    @Override
    public String toString() {
        return getName() + ": " + getDescription() + " - " + getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getProductId() == product.getProductId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }
}
