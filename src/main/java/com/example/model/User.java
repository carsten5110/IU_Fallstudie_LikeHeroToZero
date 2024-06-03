package com.example.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primärschlüssel

    @Column(name = "username", nullable = false)
    private String username; // Accountname

    @Column(name = "password", nullable = false)
    private String password; // Passwort

    @Column(name = "email", nullable = false)
    private String email; // E-Mail-Adresse

    @Column(name = "role", nullable = false)
    private String role; // Rolle des Benutzers (z.B. admin, scientist)

    @Column(name = "approved_by", nullable = false)
    private boolean approvedBy; // Genehmigungsstatus

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(boolean approvedBy) {
        this.approvedBy = approvedBy;
    }
}
