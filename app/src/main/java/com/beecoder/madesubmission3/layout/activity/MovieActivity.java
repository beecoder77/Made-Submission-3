package com.beecoder.madesubmission3.layout.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.beecoder.madesubmission3.R;
import com.beecoder.madesubmission3.model.Item;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    TextView title, description;
    ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        photo = findViewById(R.id.img_movie);
        title = findViewById(R.id.tv_title);
        description = findViewById(R.id.tv_description);

        Item item = getIntent().getParcelableExtra(EXTRA_MOVIE);
        title.setText(item.getTitle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w185" + item.getPhoto())
                .resize(200,200)
                .into(photo);
        description.setText(item.getDescription());
    }
}
