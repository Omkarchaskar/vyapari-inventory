package com.vyapari.inventorymanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String purchaseDate;

    private int quantity;

    private double pricePerUnit;

    private int minStockLevel;

    public Product() {}

    public Product(String name, String purchaseDate, int quantity, double pricePerUnit, int minStockLevel) {
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.minStockLevel = minStockLevel;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPurchaseDate() { return purchaseDate; }

    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPricePerUnit() { return pricePerUnit; }

    public void setPricePerUnit(double pricePerUnit) { this.pricePerUnit = pricePerUnit; }

    public int getMinStockLevel() { return minStockLevel; }

    public void setMinStockLevel(int minStockLevel) { this.minStockLevel = minStockLevel; }

    public boolean isLowStock() {
        return quantity < minStockLevel;
    }
}
