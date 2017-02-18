package com.cronocraft.cronocraft.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.cronocraft.cronocraft.model.User;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends Activity{

    private static String TAG = RegisterActivity.class.getSimpleName();

   private Firebase mRef;

    private TextInputLayout registerNameLayout,registerEmailLayout,registerPasswordLayout,registerMobileLayout;
    private EditText registerName,registerEmail,registerPassword,registerMobile;
    private Button registerButton,btnLinkToLogin;
    private ProgressDialog pDialog;
    private LoggedInSession session;
   private FirebaseAuth auth;
   private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        mRef = new Firebase("https://chronocraft-12989.firebaseio.com/");

        registerNameLayout = (TextInputLayout) findViewById(R.id.registerNameLayout);
        registerEmailLayout = (TextInputLayout) findViewById(R.id.registerEmailLayout);
        registerPasswordLayout = (TextInputLayout) findViewById(R.id.registerPasswordLayout);
        registerMobileLayout = (TextInputLayout) findViewById(R.id.registerMobileLayout);
        registerName = (EditText) findViewById(R.id.registerName);
        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerMobile = (EditText) findViewById(R.id.registerMobile);
        registerButton = (Button) findViewById(R.id.registerButton);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLogin);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        session = new LoggedInSession(getApplicationContext());
        registerEmail.addTextChangedListener(new RegisterActivity.MyTextWatcher(registerEmail));
        registerPassword.addTextChangedListener(new RegisterActivity.MyTextWatcher(registerPassword));
        registerMobile.addTextChangedListener(new RegisterActivity.MyTextWatcher(registerMobile));
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void submitForm(){
        if(!validateEmail()){
            return;
        }
        if(!validatePassword()){
            return;
        }
        if(!validateMobile()){
            return;
        }
        final String name = registerName.getText().toString().trim();
        final String email = registerEmail.getText().toString().trim();
        final String password = registerPassword.getText().toString().trim();
        final String mobile = registerMobile.getText().toString().trim();
        //checkRegister(name,email,password,mobile);

        pDialog.setMessage("Loading...");
        showDialog();

        setUpUser(name,email,mobile);
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                //progressBar.setVisibility(View.GONE);
                hideDialog();
                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    onAuthenticationSucess(task.getResult().getUser());
                    session.createLogin(name, email, mobile);
                    startActivity(new Intent(RegisterActivity.this, NavigationDrawerActivity.class));
                    finish();
                }
            }
        });
    }

    private void onAuthenticationSucess(FirebaseUser mUser) {
        // Write new user
        saveNewUser(mUser.getUid(), user.getName(), user.getMobile(), user.getEmail());
    }

    private void saveNewUser(String userId, String name, String phone, String email) {
        User user = new User(userId,name,email,phone);
        mRef.child("users").child(userId).setValue(user);
    }

    private void checkRegister(final String name,final String email,final String password,final String mobile){

        String tag_string_req = "req_regsiter";
        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jobj = new JSONObject(response);
                    boolean error = jobj.getBoolean("error");
                    if (!error) {
                        //session.createLogin(name, email, mobile);
                        session.setWaiting(true);
                        Intent i = new Intent(RegisterActivity.this, OtpVerifyActivity.class);
                        startActivity(i);
                        finish();
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
                params.put("password",password);
                params.put("mobile",mobile);
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
        if(registerPassword.getText().toString().trim().isEmpty()){
            registerPasswordLayout.setError("Enter Password");
            requestFocus(registerPassword);
            return false;
        }else{
            registerPasswordLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail(){
        String email = registerEmail.getText().toString().trim();

        if(email.isEmpty() || !isValidEmail(email)){
            registerEmailLayout.setError("Enter Valid Email Address");
            requestFocus(registerEmail);
            return false;
        }else{
            registerEmailLayout.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidMobile(String mobile){
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

    private boolean validateMobile(){
        String mobile = registerMobile.getText().toString().trim();

        if(mobile.isEmpty() || !isValidMobile(mobile)){
            registerMobileLayout.setError("Enter Valid Phone no.");
            requestFocus(registerMobile);
            return false;
        }
        else{
            registerMobileLayout.setErrorEnabled(false);
        }

        return true;
    }

    private class MyTextWatcher implements TextWatcher {

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
                case R.id.registerEmail:
                    validateEmail();
                    break;
                case R.id.registerPassword:
                    validatePassword();
                    break;
                case R.id.registerMobile:
                    validateMobile();
                    break;
            }
        }
    }

    protected void setUpUser(String name,String email,String mobile) {
        user = new User();
        user.setName(name);
        user.setMobile(mobile);
        user.setEmail(email);
    }
}