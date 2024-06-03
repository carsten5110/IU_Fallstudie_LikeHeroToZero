package com.example.dao;

import com.example.model.PendingEmission;
import com.example.model.Emission;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PendingEmissionDAO {
    @Inject
    private EntityManager em;

    // Speichert eine neue ausstehende Emission in der Datenbank
    @Transactional
    public void save(PendingEmission pendingEmission) {
        em.persist(pendingEmission);
    }

    // Findet alle ausstehenden Emissionen
    @Transactional
    public List<PendingEmission> findAll() {
        return em.createQuery("SELECT p FROM PendingEmission p", PendingEmission.class).getResultList();
    }

    // Löscht eine ausstehende Emission aus der Datenbank
    @Transactional
    public void delete(PendingEmission pendingEmission) {
        em.remove(em.contains(pendingEmission) ? pendingEmission : em.merge(pendingEmission));
    }

    // Genehmigt eine ausstehende Emission und verschiebt sie in die Emissionstabelle
    @Transactional
    public void approve(PendingEmission pendingEmission) {
        Emission emission = new Emission();
        emission.setCountry(pendingEmission.getCountry());
        emission.setYear(pendingEmission.getYear());
        emission.setEmissions(pendingEmission.getEmissions());
        emission.setApprovedBy(pendingEmission.getSubmittedBy());

        em.persist(emission);
        em.remove(em.contains(pendingEmission) ? pendingEmission : em.merge(pendingEmission));
    }
}
