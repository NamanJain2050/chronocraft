package com.cronocraft.cronocraft.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.LoggedInSession;
import com.cronocraft.cronocraft.helper.CircleTransform;

public class ProfileActivity extends AppCompatActivity {

    private static String urlProfileImg = "https://kenanfellows.org/wp-content/uploads/2016/02/blank-profile-picture-973460_960_720.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("User Profile");

        LoggedInSession s = new LoggedInSession(getApplicationContext());

        ImageView i = (ImageView) findViewById(R.id.profileImg);
        TextView t1 = (TextView) findViewById(R.id.profileName);
        t1.setText(s.getUserDetails().get("name"));

        TextView t2 = (TextView) findViewById(R.id.profileEmail);
        t2.setText(s.getUserDetails().get("email"));

        TextView t3 = (TextView) findViewById(R.id.profileMobile);
        t3.setText(s.getUserDetails().get("mobile"));

        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(i);

        Button b = (Button) findViewById(R.id.buttonSubscribe);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Thank you!",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
