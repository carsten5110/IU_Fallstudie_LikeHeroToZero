package com.example.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {

    @Produces
    @PersistenceContext(unitName = "sustainabilityPU")
    private EntityManager em; // Erzeugt eine EntityManager-Instanz für die Datenbankoperationen
}
