package com.dev.android_tuto_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

// public class HomeActivity extends AppCompatActivity {
//     FloatingActionButton addPersonneBtn;
//     ListView personneListView;
//     ArrayList<String> personneList = new ArrayList<>();
//     ArrayAdapter<String> adapter; 
//     PersonneDAO personneDAO = new PersonneDAO();

//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_home);

//         addPersonneBtn = findViewById(R.id.addPersonneBtn);
//         personneListView = findViewById(R.id.personneListView);

//         // Initialisez l'adaptateur une seule fois
//         adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personneList);
//         personneListView.setAdapter(adapter);

//         // bouton pour ajouter une nouvelle personne
//         addPersonneBtn.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
//                 startActivity(intent);
//             }
//         });

//         // Récupérer les personnes depuis la BD
//         loadPersonnes();

//         // Ajouter un clic sur un élément de la liste pour afficher les détails de la personne
//         personneListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//             @Override
//             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                 String selectedPersonName = personneList.get(position);
//                 Personne selectedPerson = personneDAO.getPersonne(position); // Adapter pour récupérer les bonnes données

//                 // Passer l'information à un fragment
//                 Intent intent = new Intent(HomeActivity.this, PersonneDetails.class);
//                 intent.putExtra("personne_name", selectedPerson.getNom());
//                 intent.putExtra("personne_email", selectedPerson.getEmail());
//                 intent.putExtra("personne_tele", selectedPerson.getTele());
//                 intent.putExtra("personne_dateNaiss", selectedPerson.getDateNaiss());
//                 startActivity(intent);
//             }
//         });
//     }

//     // Charger les personnes depuis la base de données
//     private void loadPersonnes() {
//         ArrayList<Personne> personnes = personneDAO.getAllPersonnes();
//         personneList.clear();
//         for (Personne personne : personnes) {
//             personneList.add(personne.getNom());
//         }

//         // Notifiez l'adaptateur des changements
//         adapter.notifyDataSetChanged();
//     }
// }

public class HomeActivity extends AppCompatActivity {
    FloatingActionButton addPersonneBtn;
    ListView personneListView;
    ArrayList<String> personneList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    static ArrayList<Personne> personnes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addPersonneBtn = findViewById(R.id.addPersonneBtn);
        personneListView = findViewById(R.id.personneListView);

        // Ajouter les utilisateurs statiques uniquement si la liste est vide
        if (personnes.isEmpty()) {
            personnes.add(new Personne("Alice", "alice@example.com", "123456789", "01/01/1990"));
            personnes.add(new Personne("Bob", "bob@example.com", "987654321", "02/02/1992"));
            personnes.add(new Personne("Charlie", "charlie@example.com", "456123789", "03/03/1993"));
        }

        // Mettre à jour la liste des noms
        personneList.clear();
        for (Personne personne : personnes) {
            personneList.add(personne.getNom());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personneList);
        personneListView.setAdapter(adapter);

        // Ajouter un clic sur un élément de la liste pour afficher les détails de la personne
        personneListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Personne selectedPerson = personnes.get(position);

                // Passer les données au fragment
                Intent intent = new Intent(HomeActivity.this, PersonneDetailsActivity.class);
                intent.putExtra("personne_name", selectedPerson.getNom());
                intent.putExtra("personne_email", selectedPerson.getEmail());
                intent.putExtra("personne_tele", selectedPerson.getTele());
                intent.putExtra("personne_dateNaiss", selectedPerson.getDateNaiss());
                startActivity(intent);
            }
        });

        addPersonneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Mettre à jour la liste des noms
        personneList.clear();
        for (Personne personne : personnes) {
            personneList.add(personne.getNom());
        }

        // Notifiez l'adaptateur des changements
        adapter.notifyDataSetChanged();
    }
}