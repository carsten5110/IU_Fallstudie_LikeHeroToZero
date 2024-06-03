package com.example.bean;

import com.example.dao.PendingEmissionDAO;
import com.example.dao.UserDAO;
import com.example.model.PendingEmission;
import com.example.model.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;

import java.text.DecimalFormat;
import java.util.List;

@Named
@RequestScoped
public class ApprovalBean {

    @Inject
    private PendingEmissionDAO pendingEmissionDAO;

    @Inject
    private UserDAO userDAO;

    // Holt alle ausstehenden Emissionsdaten
    public List<PendingEmission> getPendingEmissions() {
        return pendingEmissionDAO.findAll();
    }

    // Holt alle ausstehenden Benutzer
    public List<User> getPendingUsers() {
        return userDAO.findPendingUsers();
    }

    // Genehmigt eine Emission und verschiebt sie in die Emissionstabelle
    public String approveEmission(PendingEmission pendingEmission) {
        pendingEmissionDAO.approve(pendingEmission);
        return "approval?faces-redirect=true";
    }

    // Lehnt eine Emission ab und löscht sie aus der Pending-Tabelle
    public String rejectEmission(PendingEmission pendingEmission) {
        pendingEmissionDAO.delete(pendingEmission);
        return "approval?faces-redirect=true";
    }

    // Genehmigt einen Benutzer und aktualisiert dessen Status
    public String approveUser(User user) {
        user.setApprovedBy(true);
        userDAO.update(user);
        return "approval?faces-redirect=true";
    }

    // Lehnt einen Benutzer ab und löscht ihn aus der Benutzertabelle
    public String rejectUser(User user) {
        userDAO.delete(user);
        return "approval?faces-redirect=true";
    }

    // Prüft, ob der aktuelle Benutzer ein Admin ist
    public String checkAccess() {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user == null || !"admin".equals(user.getRole())) {
            return "index?faces-redirect=true";
        }
        return null;
    }

    // Formatiert die Emissionsdaten für die Anzeige
    public String formatEmissions(double emissions) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(emissions);
    }
}
