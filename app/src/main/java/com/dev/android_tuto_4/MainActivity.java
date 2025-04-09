package com.dev.android_tuto_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    EditText email, pwd;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.RegisterEmail);
        pwd = findViewById(R.id.pwd);
        btnLogin = findViewById(R.id.Btnlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convertir les valeurs des champs en String
                String emailText = email.getText().toString().trim();
                String pwdText = pwd.getText().toString().trim();

                // Comparer les valeurs
                if ("admin".equals(emailText) && "admin".equals(pwdText)) {
                    // Rediriger vers HomeActivity
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    email.setError("Email ou mot de passe incorrect");
                    pwd.setError("Email ou mot de passe incorrect");
                    Toast.makeText(MainActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}