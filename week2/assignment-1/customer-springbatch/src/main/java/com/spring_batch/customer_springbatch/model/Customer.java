package com.spring_batch.customer_springbatch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    private int id;
    private String name;
    private String product;

    public Customer(){}

    public Customer(int id, String name, String product) {
        this.id = id;
        this.name = name;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", product=" + product + "]";
    }

}
