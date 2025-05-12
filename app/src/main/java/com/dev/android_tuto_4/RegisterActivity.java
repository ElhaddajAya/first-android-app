package com.dev.android_tuto_4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText nom, email, dateNaiss, tele;
    Button btnRegister;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nom = findViewById(R.id.nom);
        email = findViewById(R.id.registerEmail);
        tele = findViewById(R.id.phone);
        dateNaiss = findViewById(R.id.dateNaiss);

        btnRegister = findViewById(R.id.BtnRegister);

        // Initialiser DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

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

                // Ajouter la personne dans SQLite
                boolean isInserted = databaseHelper.addPersonne(nomValue, emailValue, teleValue, dateNaissValue);

                if (isInserted) {
                    Toast.makeText(RegisterActivity.this, "Personne enregistrée avec succès", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}