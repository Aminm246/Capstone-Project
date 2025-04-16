package edu.metro.subscriptionshepard;

import jakarta.persistence.*;

// This class represents a subscription that a user might have
@Entity
@Table(name = "subscriptions")
public class Subscription {

    // Unique ID for each subscription (auto-generated)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the subscription (e.g., Netflix)
    @Column(nullable = false)
    private String name;

    // Cost of the subscription
    @Column(nullable = false)
    private double price;

    // Duration of the subscription (e.g., "1 Year")
    @Column
    private String duration;

    // How often the payment occurs (Monthly, Yearly, etc.)
    @Column(name = "payment_frequency")
    private String paymentFrequency;

    // This links the subscription to a specific user
    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key column in the database
    private User user;

    // Constructor without ID (used when creating new subscriptions)
    public Subscription(String name, double price, String duration, String paymentFrequency) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.paymentFrequency = paymentFrequency;
    }

    // Constructor with ID (used when loading existing data)
    public Subscription(Long id, String name, double price, String duration, String paymentFrequency) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.paymentFrequency = paymentFrequency;
    }

    // Default constructor (required by JPA)
    public Subscription() {}

    // Getters and setters for all fields

    public Long getId() { return id; }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public String getDuration() { return duration; }

    public String getPaymentFrequency() { return paymentFrequency; }

    // Getter for the linked User
    public User getUser() { return user; }

    // Setter for the linked User
    public void setUser(User user) { this.user = user; }

    // Setters for other fields (if needed)
    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setPrice(double price) { this.price = price; }

    public void setDuration(String duration) { this.duration = duration; }

    public void setPaymentFrequency(String paymentFrequency) { this.paymentFrequency = paymentFrequency; }
}
