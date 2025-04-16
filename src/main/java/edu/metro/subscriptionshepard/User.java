package edu.metro.subscriptionshepard;

import jakarta.persistence.*;
import java.util.List;

// This class represents a user in the system
@Entity
@Table(name = "users")
public class User {

    // Unique ID for each user (automatically generated)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user's chosen username (must be unique)
    @Column(nullable = false, unique = true)
    private String username;

    // The user's password (will be stored encrypted)
    @Column(nullable = false)
    private String password;

    // This links the user to their subscriptions (one user can have many subscriptions)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;

    // Required empty constructor for JPA
    public User() {}

    // Constructor to easily create a user with username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters to access and change the fields

    public Long getId() { return id; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public List<Subscription> getSubscriptions() { return subscriptions; }

    public void setId(Long id) { this.id = id; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
