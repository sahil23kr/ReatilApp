package com.Retail.Model;

import jakarta.persistence.*;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

@Entity
@Table(name="product")
public class Product {
    @Id
    @Column(name="productId")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productId;
    private String name;
    private double price;
    private String status;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Product(String productId, String name, double price, String status) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
