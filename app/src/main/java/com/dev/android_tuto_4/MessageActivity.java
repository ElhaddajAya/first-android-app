package com.dev.android_tuto_4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        EditText messageInput = findViewById(R.id.messageInput);
        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString().trim();

            if (message.isEmpty()) {
                // Show toast on top
                Toast.makeText(MessageActivity.this, "Veuillez entrer un message", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(MessageActivity.this, "Message envoyé", Toast.LENGTH_SHORT).show();
                // Vider le champ d'entrée
                messageInput.setText("");
            }
        });
    }
}