package com.cronocraft.cronocraft.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.CartSession;
import com.cronocraft.cronocraft.app.AppConfig;
import com.cronocraft.cronocraft.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CheckOutFormActivity extends AppCompatActivity {

    private static String TAG = CheckOutFormActivity.class.getSimpleName();

    private TextInputLayout checkOutNameLayout,checkOutEmailLayout,checkOutAddressLayout,checkOutCityLayout,checkOutPinLayout,checkOutMobileLayout;
    private EditText checkOutName,checkOutEmail,checkOutAddress,checkOutCity,checkOutPin,checkOutMobile;
    private Button checkout;
    private CartSession c;
    private ProgressDialog pDialog;
    private TextView checkOutPayMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCheckOut);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Check Out");

        checkOutName = (EditText) findViewById(R.id.checkOutName);
        checkOutEmail = (EditText) findViewById(R.id.checkOutEmail);
        checkOutAddress = (EditText) findViewById(R.id.checkOutAddress);
        checkOutCity = (EditText) findViewById(R.id.checkOutCity);
        checkOutPin = (EditText) findViewById(R.id.checkOutPinCode);
        checkOutMobile = (EditText) findViewById(R.id.checkOutPhone);
        checkout = (Button) findViewById(R.id.checkOutOrder);
        checkOutPayMode = (TextView) findViewById(R.id.checkOutPaymentMode);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);



        c = new CartSession(getApplicationContext());
        TextView checkOutCost = (TextView) findViewById(R.id.checkOutCost);
        checkOutCost.setText("Amount To Be Paid: " + c.getCartTotal());
        checkOutPayMode.setText("Payment through Instamojo(Add this code in payment of purpose): " + c.getCartCode());

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });


    }

    void submitForm(){
        String name = checkOutName.getText().toString().trim();
        String email = checkOutEmail.getText().toString().trim();
        String address = checkOutAddress.getText().toString().trim();
        String city = checkOutCity.getText().toString().trim();
        String pin = checkOutPin.getText().toString().trim();
        String mobile = checkOutMobile.getText().toString().trim();

        if(name.isEmpty() || email.isEmpty() || address.isEmpty() || city.isEmpty() || pin.isEmpty() || mobile.isEmpty()){
            Toast.makeText(getApplicationContext(),"Fill form properly",Toast.LENGTH_SHORT).show();
            return;
        }

        placeOrder(name,email,address,city,pin,mobile,Integer.toString(c.getCartTotal()),c.getCartCode());
    }

    private void placeOrder(final String name,final String email,final String address,final String city,final String pin,final String mobile,final String amount,final String code){

        String tag_string_req = "req_regsiter";
        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_CHECKOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jobj = new JSONObject(response);
                    boolean error = jobj.getBoolean("error");
                    if (!error) {
                        //session.createLogin(name, email, mobile);
                        //session.setWaiting(true);
                        //Intent i = new Intent(RegisterActivity.this, OtpVerifyActivity.class);
                        //startActivity(i);
                        //finish();
                        Toast.makeText(getApplicationContext(),"Order Placed: Redirecting...",Toast.LENGTH_SHORT).show();
                        Uri uri = Uri.parse("https://www.instamojo.com/@chronocraft/"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        finish();
                        //Intent i = new Intent(CheckOutFormActivity.this,NavigationDrawerActivity.class);
                        //startActivity(i);
                    } else {
                        String errorMsg = jobj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "JSONError: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Register Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name",name);
                params.put("email",email);
                params.put("address",address);
                params.put("city",city);
                params.put("pin",pin);
                params.put("mobile",mobile);
                params.put("amount",amount);
                params.put("code",code);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);
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

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
