package com.example.bean;

import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.util.PasswordUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class LoginBean {

    private String username;
    private String password;
    private String message;

    @Inject
    private UserDAO userDAO;

    // Methode für das Login eines Benutzers
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        User user = userDAO.findByUsername(username);

        if (user != null && PasswordUtil.verifyPassword(password, user.getPassword())) {
            if (!user.isApprovedBy()) {
                context.addMessage("loginForm:username", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ihr Konto muss zuerst genehmigt werden.", null));
                return null;
            }
            context.getExternalContext().getSessionMap().put("user", user);
            return "dataEntry?faces-redirect=true";
        } else {
            context.addMessage("loginForm:username", new FacesMessage("Accountname und/oder Passwort falsch. Überprüfen Sie ihre Eingaben."));
            return null;
        }
    }

    // Methode für das Logout eines Benutzers
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    // Holt den aktuell eingeloggten Benutzer
    public User getCurrentUser() {
        return (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    // Prüft, ob der aktuell eingeloggte Benutzer ein Admin ist
    public boolean isAdmin() {
        User user = getCurrentUser();
        return user != null && "admin".equals(user.getRole());
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
