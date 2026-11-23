package com.example.artspace;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvPosition, tvTitle, tvArtistYear;
    private ImageView ivArtwork;
    private Button btnPrevious, btnNext;

    private int currentIndex = 0;

    private final int[] images = {
            R.drawable.art1,
            R.drawable.art2,
            R.drawable.art3,
            R.drawable.art4,
            R.drawable.art5,
            R.drawable.art6
    };

    private final String[] titles = {
            "Deep Field Stellar Deaths",
            "Pillars of Creation",
            "Carina Nebula Cliffs",
            "Stephan's Quintet",
            "Southern Ring Nebula",
            "Hubble Ngc Flat Final"
    };

    private final String[] artists = {
            "Smacs Carin",
            "James Webb",
            "Cosmic Dust",
            "Pegasus Cluster",
            "Planetary Gas",
            "Sonay Billan"
    };

    private final String[] years = {
            "2021",
            "2022",
            "2022",
            "2023",
            "2020",
            "2019"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPosition = findViewById(R.id.tvPosition);
        ivArtwork = findViewById(R.id.ivArtwork);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtistYear = findViewById(R.id.tvArtistYear);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("CURRENT_INDEX", 0);
        }

        updateArtworkUI();

        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateArtworkUI();
            }
        });

        btnNext.setOnClickListener(v -> {
            if (currentIndex < images.length - 1) {
                currentIndex++;
                updateArtworkUI();
            }
        });

        // الانتقال للشاشة الثانية عند الضغط على الصورة
        ivArtwork.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
            intent.putExtra("TITLE", titles[currentIndex]);
            intent.putExtra("ARTIST", artists[currentIndex]);
            intent.putExtra("YEAR", years[currentIndex]);
            startActivity(intent);
        });
    }

    private void updateArtworkUI() {
        tvPosition.setText((currentIndex + 1) + " / " + images.length);
        ivArtwork.setImageResource(images[currentIndex]);
        tvTitle.setText(titles[currentIndex]);
        tvArtistYear.setText(artists[currentIndex] + " (" + years[currentIndex] + ")");

        boolean isFirst = (currentIndex == 0);
        btnPrevious.setEnabled(!isFirst);
        btnPrevious.setBackgroundTintList(ColorStateList.valueOf(
                ContextCompat.getColor(this, isFirst ? R.color.btn_disabled : R.color.btn_brown)
        ));

        boolean isLast = (currentIndex == images.length - 1);
        btnNext.setEnabled(!isLast);
        btnNext.setBackgroundTintList(ColorStateList.valueOf(
                ContextCompat.getColor(this, isLast ? R.color.btn_disabled : R.color.btn_brown)
        ));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CURRENT_INDEX", currentIndex);
    }
}