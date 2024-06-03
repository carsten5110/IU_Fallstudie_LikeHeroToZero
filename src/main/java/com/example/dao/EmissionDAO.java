package com.example.dao;

import com.example.model.Emission;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class EmissionDAO {
    @Inject
    private EntityManager em;

    // Findet Emissionen nach Land
    public List<Emission> findByCountry(String country) {
        TypedQuery<Emission> query = em.createQuery(
                "SELECT e FROM Emission e WHERE e.country = :country ORDER BY e.year DESC",
                Emission.class
        );
        query.setParameter("country", country);
        return query.getResultList();
    }

    // Speichert eine neue Emission in der Datenbank
    public void save(Emission emission) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(emission);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Findet die neuesten Emissionsdaten für ein Land
    public List<Emission> findLatestByCountry(String country) {
        TypedQuery<Emission> query = em.createQuery(
                "SELECT e FROM Emission e WHERE e.country = :country ORDER BY e.year DESC",
                Emission.class
        );
        query.setParameter("country", country);
        query.setMaxResults(1); // Nur den neuesten Datensatz abrufen
        return query.getResultList();
    }
}
