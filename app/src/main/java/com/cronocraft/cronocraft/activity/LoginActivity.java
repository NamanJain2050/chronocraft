package com.cronocraft.cronocraft.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.LoggedInSession;
import com.cronocraft.cronocraft.app.AppConfig;
import com.cronocraft.cronocraft.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    private static String TAG = LoginActivity.class.getSimpleName();

    private TextInputLayout loginEmailLayout,loginPasswordLayout;
    private EditText loginEmail,loginPassword;
    private Button loginButton,btnLinkToregister;
    private ProgressDialog pDialog;
    private LoggedInSession session;
   // private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // auth = FirebaseAuth.getInstance();

       // if (auth.getCurrentUser() != null) {
         //   startActivity(new Intent(LoginActivity.this, NavigationDrawerActivity.class));
           // finish();
        //}

        loginEmailLayout = (TextInputLayout) findViewById(R.id.loginEmailLayout);
        loginPasswordLayout = (TextInputLayout) findViewById(R.id.loginPasswordLayout);
        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        btnLinkToregister = (Button) findViewById(R.id.btnLinkToRegister);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        session = new LoggedInSession(getApplicationContext());
       if(session.isLoggedIn()){
            Intent i = new Intent(LoginActivity.this,NavigationDrawerActivity.class);
            startActivity(i);
            finish();
        }
        if(session.isWaiting()){
            Intent i = new Intent(LoginActivity.this,OtpVerifyActivity.class);
            startActivity(i);
            finish();
        }

        loginEmail.addTextChangedListener(new MyTextWatcher(loginEmail));
        loginPassword.addTextChangedListener(new MyTextWatcher(loginPassword));
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumbitForm();
            }
        });
        btnLinkToregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void sumbitForm(){
        if(!validateEmail()){
            return;
        }
        if(!validatePassword()){
            return;
        }
        final String email = loginEmail.getText().toString().trim();
        final String password = loginPassword.getText().toString().trim();
        checkLogin(email,password);

        //pDialog.setMessage("Loading...");
        //showDialog();

       /* auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        hideDialog();
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                loginPassword.setError("Minimum 6 characters..");
                            } else {
                                Toast.makeText(LoginActivity.this,"Auth failed!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(LoginActivity.this,NavigationDrawerActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });*/
    }

    private void checkLogin(final String email,final String password){

        String tag_string_req = "req_login";
        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String mobile = user.getString("mobile");
                        Log.d(TAG,name);
                        Log.d(TAG,email);
                        Log.d(TAG,mobile);
                        session.createLogin(name, email,mobile);
                        Intent i = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        String errorMsg = jObj.getString("error_msg");
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
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword(){
        if(loginPassword.getText().toString().trim().isEmpty()){
            loginPasswordLayout.setError("Enter Password");
            requestFocus(loginPassword);
            return false;
        }else{
            loginPasswordLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail(){
        String email = loginEmail.getText().toString().trim();

        if(email.isEmpty() || !isValidEmail(email)){
            loginEmailLayout.setError("Enter Valid Email Address");
            requestFocus(loginEmail);
            return false;
        }else{
            loginEmailLayout.setErrorEnabled(false);
        }

        return true;
    }

    private class MyTextWatcher implements TextWatcher{

        private View view;

        private MyTextWatcher(View view){
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence,int i,int i1,int i2){

        }
        public void onTextChanged(CharSequence charSequence,int i,int i1,int i2){

        }
        public void afterTextChanged(Editable editable){
            switch (view.getId()){
                case R.id.loginEmail:
                    validateEmail();
                    break;
                case R.id.loginPassword:
                    validatePassword();
                    break;
            }
        }
    }
}
