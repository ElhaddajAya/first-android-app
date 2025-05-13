package com.dev.android_tuto_4;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private EditText messageInput;
    private ImageButton cameraButton, sendButton;
    private ListView messageListView;
    private ArrayList<String> messages = new ArrayList<>();
    private MessageAdapter adapter;
    private ImageView capturedImageView; // Pour afficher l'image capturée
    private Bitmap capturedImageBitmap; // Pour stocker l'image capturée

    private ActivityResultLauncher<Intent> cameraLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // Initialiser les vues
        messageInput = findViewById(R.id.messageInput);
        cameraButton = findViewById(R.id.cameraButton);
        sendButton = findViewById(R.id.sendButton);
        messageListView = findViewById(R.id.messageListView);
        capturedImageView = findViewById(R.id.capturedImageView); // Ajoutez cette vue dans votre layout

        // Initialiser l'adaptateur pour la liste des messages
        adapter = new MessageAdapter(this, messages);
        messageListView.setAdapter(adapter);

        // Initialiser le launcher pour la caméra
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bundle extras = result.getData().getExtras();
                        capturedImageBitmap = (Bitmap) extras.get("data"); // Stocker l'image capturée
                        capturedImageView.setVisibility(View.VISIBLE); // Rendre l'ImageView visible
                        capturedImageView.setImageBitmap(capturedImageBitmap); // Afficher l'image capturée
                    } else {
                        Toast.makeText(this, "Capture annulée", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Gérer le clic sur le bouton de la caméra
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    cameraLauncher.launch(cameraIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MessageActivity.this, "Aucune application de caméra trouvée", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Gérer le clic sur le bouton d'envoi
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageInput.getText().toString().trim();

                if (!message.isEmpty() || capturedImageBitmap != null) {
                    // Ajouter le message et l'image à la liste des messages
                    if (!message.isEmpty()) {
                        messages.add(message);
                    }
                    if (capturedImageBitmap != null) {
                        messages.add("Image envoyée"); // Vous pouvez gérer l'affichage de l'image différemment
                        capturedImageBitmap = null; // Réinitialiser après l'envoi
                        capturedImageView.setVisibility(View.GONE); // Masquer l'aperçu
                    }

                    adapter.notifyDataSetChanged(); // Mettre à jour la liste
                    messageInput.setText(""); // Effacer le champ de saisie
                } else {
                    Toast.makeText(MessageActivity.this, "Veuillez écrire un message ou capturer une image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
