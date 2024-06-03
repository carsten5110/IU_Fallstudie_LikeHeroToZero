package com.example.bean;

import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.util.PasswordUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.regex.Pattern;

@Named
@RequestScoped
public class RegisterBean {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    @Inject
    private UserDAO userDAO;

    // Methode zur Registrierung eines neuen Benutzers
    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();

        // Überprüft, ob der Benutzername den Anforderungen entspricht
        if (!isUsernameValid(username)) {
            context.addMessage("registerForm:username", new FacesMessage("Der Accountname entspricht nicht den Anforderungen"));
            return null;
        }

        // Überprüft, ob der Benutzername bereits vergeben ist
        if (isUsernameTaken(username)) {
            context.addMessage("registerForm:username", new FacesMessage("Der Accountname ist bereits vergeben."));
            return null;
        }

        // Überprüft, ob die E-Mail-Adresse den Anforderungen entspricht
        if (!isEmailValid(email)) {
            context.addMessage("registerForm:email", new FacesMessage("Die E-Mail-Adresse entspricht nicht den Anforderungen."));
            return null;
        }

        // Überprüft, ob das Passwort den Anforderungen entspricht
        if (!isPasswordValid(password)) {
            context.addMessage("registerForm:password", new FacesMessage("Das Passwort entspricht nicht den Anforderungen."));
            return null;
        }

        // Überprüft, ob die Passwörter übereinstimmen
        if (!password.equals(confirmPassword)) {
            context.addMessage("registerForm:confirmPassword", new FacesMessage("Passwörter stimmen nicht überein."));
            return null;
        }

        // Hash das Passwort
        String hashedPassword = PasswordUtil.hashPassword(password);

        // Erstellt einen neuen Benutzer und speichert ihn in der Datenbank
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setEmail(email);
        user.setRole("scientist"); // Standardrolle für neue Benutzer
        user.setApprovedBy(false); // Benutzer muss genehmigt werden

        userDAO.save(user);

        // Fügt eine Erfolgsnachricht hinzu
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ihr Account wurde erstellt, muss aber noch genehmigt werden.", null));

        return null;
    }

    // Überprüft, ob der Benutzername gültig ist
    private boolean isUsernameValid(String username) {
        return username != null && username.length() >= 3;
    }

    // Überprüft, ob der Benutzername bereits vergeben ist
    private boolean isUsernameTaken(String username) {
        try {
            User existingUser = userDAO.findByUsername(username);
            return existingUser != null;
        } catch (Exception e) {
            return false;
        }
    }

    // Überprüft, ob die E-Mail-Adresse gültig ist
    private boolean isEmailValid(String email) {
        return email != null && email.contains("@");
    }

    // Überprüft, ob das Passwort gültig ist
    private boolean isPasswordValid(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Pattern.matches("[^a-zA-Z0-9]", String.valueOf(c))) {
                hasSpecialChar = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }

    // Getter und Setter
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
