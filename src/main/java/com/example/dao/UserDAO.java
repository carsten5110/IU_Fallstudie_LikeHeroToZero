package com.example.dao;

import com.example.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserDAO {

    @Inject
    private EntityManager em;

    // Speichert einen neuen Benutzer in der Datenbank
    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    // Findet einen Benutzer anhand seines Benutzernamens
    public User findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Findet alle Benutzer, die noch genehmigt werden müssen
    @Transactional
    public List<User> findPendingUsers() {
        return em.createQuery("SELECT u FROM User u WHERE u.approvedBy = false", User.class).getResultList();
    }

    // Aktualisiert die Informationen eines Benutzers
    @Transactional
    public void update(User user) {
        em.merge(user);
    }

    // Löscht einen Benutzer aus der Datenbank
    @Transactional
    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }
}
