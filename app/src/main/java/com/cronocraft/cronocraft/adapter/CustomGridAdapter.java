package com.cronocraft.cronocraft.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.FavouriteSession;
import com.cronocraft.cronocraft.model.FavouriteItem;
import com.cronocraft.cronocraft.watches.WatchEightActivity;
import com.cronocraft.cronocraft.watches.WatchFiveActivity;
import com.cronocraft.cronocraft.watches.WatchFourActivity;
import com.cronocraft.cronocraft.watches.WatchOneActivity;
import com.cronocraft.cronocraft.watches.WatchSevenActivity;
import com.cronocraft.cronocraft.watches.WatchSixActivity;
import com.cronocraft.cronocraft.watches.WatchThreeActivity;
import com.cronocraft.cronocraft.watches.WatchTwoActivity;

import java.util.ArrayList;

/**
 * Created by naman on 28-11-2016.
 */

public class CustomGridAdapter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<FavouriteItem> favItems;
    private FavouriteSession session;

    public CustomGridAdapter(Activity activity){
        this.activity = activity;
        session = new FavouriteSession(activity);
        this.favItems = session.getFavorites();
        update();
    }

    @Override
    public int getCount() {
        return favItems.size();
    }

    @Override
    public Object getItem(int location) {
        return favItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    void update(){
        this.notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.grid_fav_box, null);

        ImageView img = (ImageView) convertView.findViewById(R.id.favWatchImg);
        TextView t1 = (TextView) convertView.findViewById(R.id.favWatchName);
        TextView t2 = (TextView) convertView.findViewById(R.id.favWatchCost);
        Button btn = (Button) convertView.findViewById(R.id.favWatchRemoveButton);

        final FavouriteItem f = favItems.get(position);

        img.setImageResource(f.getImgId());
        t1.setText(f.getName());
        t2.setText("Rs. " + f.getCost());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Removed!",Toast.LENGTH_SHORT).show();
                session.removeFavItem(favItems.get(position));
                favItems = session.getFavorites();
                update();
            }
        });

        View.OnClickListener clckL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f.getId() == 1)
                {
                    Intent i = new Intent(activity, WatchOneActivity.class);
                    activity.startActivity(i);
                }

                if(f.getId() == 2)
                {
                    Intent i = new Intent(activity, WatchTwoActivity.class);
                    activity.startActivity(i);
                }

                if(f.getId() == 3)
                {
                    Intent i = new Intent(activity, WatchThreeActivity.class);
                    activity.startActivity(i);
                }
                if(f.getId() == 4)
                {
                    Intent i = new Intent(activity, WatchFourActivity.class);
                    activity.startActivity(i);
                }
                if(f.getId() == 5)
                {
                    Intent i = new Intent(activity, WatchFiveActivity.class);
                    activity.startActivity(i);
                }

                if(f.getId() == 6)
                {
                    Intent i = new Intent(activity, WatchSixActivity.class);
                    activity.startActivity(i);
                }

                if(f.getId() == 7)
                {
                    Intent i = new Intent(activity, WatchSevenActivity.class);
                    activity.startActivity(i);
                }

                if(f.getId() == 8)
                {
                    Intent i = new Intent(activity, WatchEightActivity.class);
                    activity.startActivity(i);
                }
            }
        };

        img.setOnClickListener(clckL);

        return convertView;
    }
}
