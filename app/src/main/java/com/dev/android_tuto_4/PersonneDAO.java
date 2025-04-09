package com.dev.android_tuto_4;

import org.jetbrains.annotations.Debug;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonneDAO {

    // Connexion à la base de données
    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://10.0.2.2:3306/users_android";
        String user = "root";
        String password = "root";
        // Log de connexion
        System.out.println("Connexion à la base de données...");
        return DriverManager.getConnection(url, user, password);
    }

    // Enregistrer un utilisateur dans la base de données
    public boolean registerPersonne(Personne personne) {
        try (Connection connection = connect()) {
            String query = "INSERT INTO personnes (nom, tele, email, dateNaiss) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, personne.getNom());
            stmt.setString(2, personne.getTele());
            stmt.setString(3, personne.getEmail());
            stmt.setString(4, personne.getDateNaiss());
            int rowsInserted = stmt.executeUpdate();
            System.out.println("Nombre de lignes insérées : " + rowsInserted);
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'enregistrement de la personne : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Récupérer une personne par son ID
    public Personne getPersonne(int id) {
        try (Connection connection = connect()) {
            String query = "SELECT * FROM personnes WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Personne(rs.getString("nom"), rs.getString("tele"), rs.getString("email"), rs.getString("dateNaiss"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer toutes les personnes
    public ArrayList<Personne> getAllPersonnes() {
        ArrayList<Personne> personnes = new ArrayList<>();

        try (Connection connection = connect()) {
            String query = "SELECT * FROM personnes";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personnes;
    }
}

