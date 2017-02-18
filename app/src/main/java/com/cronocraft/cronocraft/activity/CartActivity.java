package com.cronocraft.cronocraft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.CartSession;
import com.cronocraft.cronocraft.adapter.CustomListAdapter;

public class CartActivity extends AppCompatActivity {

    //private List<CartItem> cartItemList = new ArrayList<CartItem>();
    private ListView listView;
    private CustomListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = (ListView) findViewById(R.id.cart_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCart);
        TextView total = (TextView) findViewById(R.id.cartTotal);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cart");

        adapter = new CustomListAdapter(this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Button cnt = (Button) findViewById(R.id.continueShopping);
        Button chckout = (Button) findViewById(R.id.checkout);
        cnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this,NavigationDrawerActivity.class);
                startActivity(i);
                finish();
            }
        });
        chckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this,CheckOutFormActivity.class);
                startActivity(i);
                finish();
            }
        });

        CartSession s = new CartSession(this);
        total.setText("Total Payable:\t\t\t  Rs. " + s.getCartTotal());
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
