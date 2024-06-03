package com.example.util;

import com.example.dao.UserDAO;
import com.example.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Startup
@Singleton
public class InitialSetup {

    @Inject
    private UserDAO userDAO; // DAO für Benutzeroperationen

    @PostConstruct
    public void init() {
        // Überprüft, ob der Admin-Benutzer existiert, und erstellt ihn, falls nicht
        if (userDAO.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(PasswordUtil.hashPassword("PasswordExample1!!"));
            admin.setEmail("admin@example.com");
            admin.setRole("admin");
            admin.setApprovedBy(true); // Admin-Benutzer wird automatisch genehmigt
            userDAO.save(admin);
            System.out.println("Admin erstellt mit Accountnamen 'admin' and passwort 'PasswordExample1!!'");
        }
    }
}
