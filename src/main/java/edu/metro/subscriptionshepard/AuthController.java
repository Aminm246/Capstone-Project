package edu.metro.subscriptionshepard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// This controller manages login and registration pages and user sign-up
@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Show the login page when user visits /login URL
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // This will load login.html template
    }

    // Show the registration page when user visits /register URL
    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());  // Add empty User object for form binding
        return "register";  // This will load register.html template
    }

    // Handle form submission from registration page
    @PostMapping("/register")
    public String registerUser(User user) {
        // Check if username already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            // If username exists, redirect back to registration page with error (you can enhance this later)
            return "redirect:/register?error";
        }

        // Encrypt the password before saving to database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the new user
        userRepository.save(user);

        // After successful registration, redirect to login page
        return "redirect:/login?success";
    }
}
