package com.cronocraft.cronocraft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.service.HttpService;

public class OtpVerifyActivity extends Activity {

    final private String TAG = OtpVerifyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);

        final EditText inputOtp = (EditText) findViewById(R.id.inputOtp);
        Button btnEnterOtp = (Button) findViewById(R.id.enterOtp);

        //ProgressDialog pDialog = new ProgressDialog(this);

        btnEnterOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = inputOtp.getText().toString().trim();
                if(!otp.isEmpty()){
                    verifyOtp(otp);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Please enter your otp!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    private void verifyOtp(String otp) {
        Log.d(TAG,"in verifyOTP");
        Intent grapprIntent = new Intent(getApplicationContext(), HttpService.class);
        grapprIntent.putExtra("otp", otp);
        startService(grapprIntent);
        Log.d(TAG,"After start intent");
        finish();
    }
}
