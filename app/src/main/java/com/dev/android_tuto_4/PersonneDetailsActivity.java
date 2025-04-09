package com.dev.android_tuto_4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PersonneDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_personne_details);

        // Récupérer les données passées
        String nom = getIntent().getStringExtra("personne_name");
        String email = getIntent().getStringExtra("personne_email");
        String tele = getIntent().getStringExtra("personne_tele");
        String dateNaiss = getIntent().getStringExtra("personne_dateNaiss");

        // Afficher les données
        TextView nomTextView = findViewById(R.id.nomTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView teleTextView = findViewById(R.id.teleTextView);
        TextView dateNaissTextView = findViewById(R.id.dateNaissTextView);

        nomTextView.setText(nom);
        emailTextView.setText(email);
        teleTextView.setText(tele);
        dateNaissTextView.setText(dateNaiss);

        // Bouton pour appeler
        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + tele));
            startActivity(callIntent);
        });

        // Bouton pour envoyer un message
        Button messageButton = findViewById(R.id.messageButton);
        messageButton.setOnClickListener(v -> {
            Intent messageIntent = new Intent(this, MessageActivity.class);
            messageIntent.putExtra("personne_name", nom);
            startActivity(messageIntent);
        });

        // Bouton pour ouvrir la caméra
        Button cameraButton = findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(cameraIntent);
        });
    }
}