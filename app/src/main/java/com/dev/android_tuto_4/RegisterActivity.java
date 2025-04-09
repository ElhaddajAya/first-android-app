package com.dev.android_tuto_4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText nom, email, dateNaiss, tele;
    Button btnRegister;
    PersonneDAO personneDAO = new PersonneDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nom = findViewById(R.id.nom);
        email = findViewById(R.id.registerEmail);
        tele = findViewById(R.id.phone);
        dateNaiss = findViewById(R.id.dateNaiss);

        btnRegister = findViewById(R.id.BtnRegister);

        // btnRegister.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
        //         String nomValue = nom.getText().toString();
        //         String emailValue = email.getText().toString();
        //         String teleValue = tele.getText().toString();
        //         String dateNaissValue = dateNaiss.getText().toString();

        //         Personne personne = new Personne(nomValue, teleValue, emailValue, dateNaissValue);
        //         if (personneDAO.registerPersonne(personne)) {
        //             Toast.makeText(RegisterActivity.this, "Personne enregistrée avec succès", Toast.LENGTH_SHORT).show();
        //             finish();
        //         } else {
        //             Toast.makeText(RegisterActivity.this, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show();
        //         }
        //     }
        // });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomValue = nom.getText().toString();
                String emailValue = email.getText().toString();
                String teleValue = tele.getText().toString();
                String dateNaissValue = dateNaiss.getText().toString();
        
                if (nomValue.isEmpty() || emailValue.isEmpty() || teleValue.isEmpty() || dateNaissValue.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }
        
                // Créer une nouvelle personne
                Personne personne = new Personne(nomValue, emailValue, teleValue, dateNaissValue);
        
                // Ajouter la personne à la liste statique
                HomeActivity.personnes.add(personne);

                Toast.makeText(RegisterActivity.this, "Personne enregistrée avec succès", Toast.LENGTH_SHORT).show();
        
                // Retourner à l'activité précédente
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}