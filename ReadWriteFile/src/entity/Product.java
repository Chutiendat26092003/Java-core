package entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Product {
    private long id;
    private String name;
    private String producer;
    private String lineProduct;
    private long price;

    // format
    private LocalDateTime insertedTime;
    private LocalDateTime updatedTime;
    private void beforeInsert() {
        this.insertedTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }
    private void beforeUpdate(){
        this.updatedTime = LocalDateTime.now();
    }
    public Product() {}

    public Product(long id, String name, String producer, String line_product, long price, Timestamp timestamp, String updated_time) {}

    public Product(long id) {
        this.id = id;
    }


    public Product(long id, String name, String producer, String lineProduct, long price) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.lineProduct = lineProduct;
        this.price = price;
    }

    public Product(long id, String name, String producer, String lineProduct, long price, LocalDateTime insertedTime, LocalDateTime updatedTime) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.lineProduct = lineProduct;
        this.price = price;
        this.insertedTime = insertedTime;
        this.updatedTime = updatedTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getLineProduct() {
        return lineProduct;
    }

    public void setLineProduct(String lineProduct) {
        this.lineProduct = lineProduct;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public LocalDateTime getInsertedTime() {
        return insertedTime;
    }

    public void setInsertedTime(LocalDateTime insertedTime) {
        this.insertedTime = insertedTime;
    }

    public void setBeforeInsertedTime() {
        beforeInsert();
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setBeforeUpdatedTime() {
        beforeUpdate();
    }

    @Override
    public String toString() {
        return  "{id=" + id +
                ",name='" + name +
                "',producer='" + producer +
                "',lineProduct='" + lineProduct +
                "',price=" + price + "}";
    }
}
