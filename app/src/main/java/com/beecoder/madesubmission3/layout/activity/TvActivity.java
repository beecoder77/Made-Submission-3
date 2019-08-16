package com.beecoder.madesubmission3.layout.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.beecoder.madesubmission3.R;
import com.beecoder.madesubmission3.model.Item;
import com.squareup.picasso.Picasso;

public class TvActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";

    TextView title, release, description;
    ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        photo = findViewById(R.id.img_tv);
        title = findViewById(R.id.tv_title);
        description = findViewById(R.id.tv_description);

        Item item = getIntent().getParcelableExtra(EXTRA_TV);
        Picasso.get().load("https://image.tmdb.org/p/w500" + item.getPhoto()).into(photo);
        title.setText(item.getTitle());
        description.setText(item.getDescription());
    }
}
