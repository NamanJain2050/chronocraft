package com.cronocraft.cronocraft.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cronocraft.cronocraft.Session.LoggedInSession;
import com.cronocraft.cronocraft.activity.NavigationDrawerActivity;
import com.cronocraft.cronocraft.app.AppConfig;
import com.cronocraft.cronocraft.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by naman on 23-11-2016.
 */

public class HttpService extends IntentService{

    //private ProgressDialog pDialog = new ProgressDialog(getApplicationContext());

    private static String TAG = HttpService.class.getSimpleName();

    public HttpService(){
        super(HttpService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent){
        if(intent != null){
            Log.d(TAG,"In httpService");
            String otp = intent.getStringExtra("otp");
            verifyOtp(otp);
        }
    }

    private void verifyOtp(final String otp){
        String tag_string_req = "req_verify_otp";
        //pDialog.setMessage("Verifying...");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_VERIFY_OTP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                //hideDialog();
                try {
                    JSONObject jobj = new JSONObject(response);
                    boolean error = jobj.getBoolean("error");
                    String message = jobj.getString("message");
                    if (!error) {
                        JSONObject profileObj = jobj.getJSONObject("profile");
                        String name = profileObj.getString("name");
                        String email = profileObj.getString("email");
                        String mobile = profileObj.getString("mobile");
                        LoggedInSession session = new LoggedInSession(getApplicationContext());
                        session.setWaiting(false);
                        session.createLogin(name, email, mobile);
                        Intent intent = new Intent(HttpService.this, NavigationDrawerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("otp", otp);

                Log.e(TAG, "Posting params: " + params.toString());
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(strReq);
    }

   /* private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }*/
}
