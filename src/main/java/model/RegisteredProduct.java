package model;

import java.util.Date;

public class RegisteredProduct {
    private int regId;
    private String serialNo;
    private Date purchaseDate;
    private String productName;
    private String model;
    private String description;

    public RegisteredProduct(int regId, String serialNo, Date purchaseDate, String productName, String model, String description) {
        this.regId = regId;
        this.serialNo = serialNo;
        this.purchaseDate = purchaseDate;
        this.productName = productName;
        this.model = model;
        this.description = description;
    }

    // Getters and Setters
    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
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
