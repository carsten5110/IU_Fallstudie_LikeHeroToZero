package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pending_emission")
public class PendingEmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primärschlüssel

    private String country; // Land
    private int year; // Jahr
    private double emissions; // Emissionen in Tonnen

    @ManyToOne
    @JoinColumn(name = "submitted_by")
    private User submittedBy; // Der Benutzer, der die Emission eingereicht hat

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getEmissions() {
        return emissions;
    }

    public void setEmissions(double emissions) {
        this.emissions = emissions;
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(User submittedBy) {
        this.submittedBy = submittedBy;
    }
}
