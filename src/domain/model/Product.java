package domain.model;

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
            throw new DomainException("Give a valid price");
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

}
