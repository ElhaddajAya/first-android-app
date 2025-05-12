package com.dev.android_tuto_4;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHelper databaseHelper;
    FloatingActionButton addPersonneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = findViewById(R.id.personneListView);
        databaseHelper = new DatabaseHelper(this);

        addPersonneBtn = findViewById(R.id.addPersonneBtn);
        addPersonneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirige vers RegisterActivity
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Récupérer les données et les afficher
        Cursor cursor = databaseHelper.getAllPersonnes();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"nom", "email"},
                new int[]{android.R.id.text1, android.R.id.text2},
                0
        );
        listView.setAdapter(adapter);
    }
}