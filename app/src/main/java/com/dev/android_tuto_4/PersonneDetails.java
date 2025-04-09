package com.dev.android_tuto_4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PersonneDetails extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonneDetails() {
        // Required empty public constructor
    }

    public static PersonneDetails newInstance(String param1, String param2) {
        PersonneDetails fragment = new PersonneDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personne_details, container, false);

        // Recupperer les donnees passees depuis HomeActivity
        Bundle args = getArguments();

        if(args != null) {
            String nom = args.getString("personne_name");
            String tele = args.getString("personne_tele");
            String email = args.getString("personne_email");
            String dateNaiss = args.getString("personne_dateNaiss");

            // Afficher les donnees dans le fragment
            TextView nomTextView = view.findViewById(R.id.nomTextView);
            TextView emailTextView = view.findViewById(R.id.emailTextView);
            TextView teleTextView = view.findViewById(R.id.teleTextView);
            TextView dateNaissTextView = view.findViewById(R.id.dateNaissTextView);

            nomTextView.setText(nom);
            emailTextView.setText(email);
            teleTextView.setText(tele);
            dateNaissTextView.setText(dateNaiss);
        }

        return view;
    }
}