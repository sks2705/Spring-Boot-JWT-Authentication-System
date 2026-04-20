package com.bezkoder.springjwt.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(ERole.ROLE_USER));
            roleRepository.save(new Role(ERole.ROLE_MODERATOR));
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
        }

        // Create default users
        if (userRepository.count() == 0) {
            // Default User
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("password123"));
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleRepository.findByName(ERole.ROLE_USER).get());
            user.setRoles(userRoles);
            userRepository.save(user);

            // Default Moderator
            User moderator = new User();
            moderator.setUsername("moderator");
            moderator.setEmail("moderator@example.com");
            moderator.setPassword(passwordEncoder.encode("password123"));
            Set<Role> modRoles = new HashSet<>();
            modRoles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).get());
            moderator.setRoles(modRoles);
            userRepository.save(moderator);

            // Default Admin
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("password123"));
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
            adminRoles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).get());
            adminRoles.add(roleRepository.findByName(ERole.ROLE_USER).get());
            admin.setRoles(adminRoles);
            userRepository.save(admin);

            System.out.println("✅ Default users created successfully!");
            System.out.println("   User: user / password123");
            System.out.println("   Moderator: moderator / password123");
            System.out.println("   Admin: admin / password123");
        }
    }
}
