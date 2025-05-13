package com.dev.android_tuto_4;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    private static final String ARG_PERSONNE_ID = "personne_id";

    public static DetailFragment newInstance(long id) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PERSONNE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        long id = getArguments().getLong(ARG_PERSONNE_ID, -1);
        if (id != -1) {
            DatabaseHelper db = new DatabaseHelper(requireContext());
            Cursor cursor = db.getPersonneById(id);
            if (cursor != null && cursor.moveToFirst()) {
                String nom = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String tele = cursor.getString(cursor.getColumnIndexOrThrow("tele"));
                String dateNaiss = cursor.getString(cursor.getColumnIndexOrThrow("date_naiss"));

                ((TextView) view.findViewById(R.id.detailNom)).setText(nom);
                ((TextView) view.findViewById(R.id.detailEmail)).setText(email);
                ((TextView) view.findViewById(R.id.detailTele)).setText(tele);
                ((TextView) view.findViewById(R.id.detailDateNaiss)).setText(dateNaiss);

                // 🔹 Bouton Appeler
                view.findViewById(R.id.callButton).setOnClickListener(v -> {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + tele));
                    startActivity(callIntent);
                });

                // 🔹 Bouton Message
                view.findViewById(R.id.messageButton).setOnClickListener(v -> {
                    Intent intent = new Intent(requireContext(), MessageActivity.class);
                    intent.putExtra("nom", nom); // Passer le nom pour afficher dans MessageActivity si besoin
                    startActivity(intent);
                });
            }
        }
    }
}
