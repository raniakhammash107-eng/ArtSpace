package com.example.artspace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_details);

        TextView tvReceivedDetails = findViewById(R.id.tvReceivedDetails);
        Button btnVisitWebsite = findViewById(R.id.btnVisitWebsite);
        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        String artist = intent.getStringExtra("ARTIST");
        String year = intent.getStringExtra("YEAR");

        String fullInfo = "Artwork Title: " + title + "\n"
                + "Artist Name: " + artist + "\n"
                + "Creation Year: " + year;
        tvReceivedDetails.setText(fullInfo);

        btnVisitWebsite.setOnClickListener(v -> {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://science.nasa.gov/mission/webb/"));
            startActivity(webIntent);
        });

        btnBack.setOnClickListener(v -> finish());
    }
}