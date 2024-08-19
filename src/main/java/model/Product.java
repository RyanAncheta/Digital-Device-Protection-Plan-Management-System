package model;

public class Product {
    private int productId;
    private String productName;
    private String model;
    private String description;

    // Default constructor
    public Product() {}

    // Constructor with parameters
    public Product(int productId, String productName, String model, String description) {
        this.productId = productId;
        this.productName = productName;
        this.model = model;
        this.description = description;
    }

    // Getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
