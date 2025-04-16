package edu.metro.subscriptionshepard;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// This interface lets you easily interact with the users table in the database
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by their username (used for login)
    Optional<User> findByUsername(String username);

    // Check if a username already exists (used during registration)
    boolean existsByUsername(String username);
}
