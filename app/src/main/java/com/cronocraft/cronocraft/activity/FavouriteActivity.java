package com.cronocraft.cronocraft.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.GridView;

import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.adapter.CustomGridAdapter;

public class FavouriteActivity extends AppCompatActivity {

    private GridView gridView;
    private CustomGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        gridView = (GridView) findViewById(R.id.grid_view_fav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFavourite);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favourites");

        adapter = new CustomGridAdapter(this);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
