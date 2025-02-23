package edu.metro.subscriptionshepard;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column
    private String duration;

    @Column(name = "payment_frequency")
    private String paymentFrequency;

    // Without id constructor
    public Subscription(String name, double price, String duration, String paymentFrequency) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.paymentFrequency = paymentFrequency;
    }

    // With id constructor
    public Subscription(int id, String name, double price, String duration, String paymentFrequency) {
        this.id = (long) id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.paymentFrequency = paymentFrequency;
    }

    // No arg constructor (required by JPA)
    public Subscription() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }
}
