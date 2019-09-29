package com.vinodh.retrofitrecyclerviewtransition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vinodh.retrofitrecyclerviewtransition.Model.MarvelHeros;
import com.vinodh.retrofitrecyclerviewtransition.Utils.AppConstants;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.vinodh.retrofitrecyclerviewtransition.Adapter.RecyclerviewAdapter.herosList;

public class NewScreenActivity extends AppCompatActivity {
    private CircleImageView profile_id;
    private TextView name_tv,author_tv,desc_tv,team_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_screen);

        Intent intent = getIntent();
        int position = intent.getIntExtra(AppConstants.POSITION,0);

        profile_id = (CircleImageView) findViewById(R.id.profile_id);
        name_tv = (TextView) findViewById(R.id.name_id);
        author_tv = (TextView) findViewById(R.id.author_id);
        desc_tv = (TextView) findViewById(R.id.desc_id);
        team_tv = (TextView) findViewById(R.id.team_id);

        MarvelHeros heros = herosList.get(position);

        Glide.with(NewScreenActivity.this)
                .asBitmap().load(heros.getImageurl())
                .into(profile_id);

        name_tv.setText(heros.getName());
        author_tv.setText(heros.getCreatedby());
        team_tv.setText(heros.getTeam());
        desc_tv.setText(heros.getBio());
        desc_tv.setMovementMethod(new ScrollingMovementMethod());
    }
}
