package com.dev.android_tuto_4;

public class Personne {
    private String nom;
    private String email;
    private String tele;
    private String dateNaiss;

    public Personne(String nom, String email, String tele, String dateNaiss) {
        this.nom = nom;
        this.email = email;
        this.tele = tele;
        this.dateNaiss = dateNaiss;
    }

    // Getters et setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTele() { return tele; }
    public void setTele(String tele) { this.tele = tele; }

    public String getDateNaiss() { return dateNaiss; }
    public void setDateNaiss(String dateNaiss) { this.dateNaiss = this.dateNaiss; }
}

