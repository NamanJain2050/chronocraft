package com.cronocraft.cronocraft.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.CartSession;
import com.cronocraft.cronocraft.app.AppController;
import com.cronocraft.cronocraft.model.CartItem;

import java.util.ArrayList;

/**
 * Created by naman on 27-11-2016.
 */

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<CartItem> cartItems;
    private CartSession session;
    ImageLoader imageLoader1 = AppController.getInstance().getImageLoader();
    ImageLoader imageLoader2 = AppController.getInstance().getImageLoader();
    ImageLoader imageLoader3 = AppController.getInstance().getImageLoader();
    ImageLoader imageLoader4 = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity){
        this.activity = activity;
        session = new CartSession(activity);
        this.cartItems = session.getFavorites();
        update();
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int location) {
        return cartItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader1 == null)
            imageLoader1 = AppController.getInstance().getImageLoader();

        if (imageLoader2 == null)
            imageLoader2 = AppController.getInstance().getImageLoader();

        if (imageLoader3 == null)
            imageLoader3 = AppController.getInstance().getImageLoader();

        if (imageLoader4 == null)
            imageLoader4 = AppController.getInstance().getImageLoader();

        ImageView cartStrap = (ImageView) convertView.findViewById(R.id.cartStrap);
        ImageView cartDial = (ImageView) convertView.findViewById(R.id.cartDial);
        ImageView cartCase = (ImageView) convertView.findViewById(R.id.cartCase);
        ImageView cartTopRing = (ImageView) convertView.findViewById(R.id.cartTopRing);

        TextView cartCost = (TextView) convertView.findViewById(R.id.cartCost);
        TextView cartStrapTitle = (TextView) convertView.findViewById(R.id.cartStrapTitle);
        TextView cartDialTitle = (TextView) convertView.findViewById(R.id.cartDialTitle);
        TextView cartCaseTitle = (TextView) convertView.findViewById(R.id.cartCaseTitle);
        TextView cartTopRingTitle = (TextView) convertView.findViewById(R.id.cartTopRingTitle);
        Button cartRemove = (Button) convertView.findViewById(R.id.cartRemoveFromButton);

        final CartItem c = cartItems.get(position);
        cartStrap.setImageResource(c.getPreview().get("strap"));
        cartDial.setImageResource(c.getPreview().get("dial"));
        cartCase.setImageResource(c.getPreview().get("case"));
        cartTopRing.setImageResource(c.getPreview().get("topRing"));

        cartCost.setText("Rs. " + c.getCost());
        cartStrapTitle.setText("Strap: " + c.getNames().get("strap"));
        cartDialTitle.setText("Dial: " + c.getNames().get("dial"));
        cartCaseTitle.setText("Case: " + c.getNames().get("case"));
        cartTopRingTitle.setText("Top Ring: " + c.getNames().get("topRing"));


        cartRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Removed!",Toast.LENGTH_SHORT).show();
                session.removeCartItem(cartItems.get(position));
                cartItems = session.getFavorites();
                update();
            }
        });
        return convertView;
    }

}
