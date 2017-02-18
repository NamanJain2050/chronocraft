package com.cronocraft.cronocraft.Session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by naman on 06-11-2016.
 */

public class LoggedInSession {

    public static String TAG = LoggedInSession.class.getSimpleName();

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "cronocraft-loggedIn";
    private static final String IS_LOGGED_IN = "isLoggedIn";
    private static final String IS_WAITING_FOR_SMS = "isWaitingSms";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE = "mobile";

    public LoggedInSession(Context c){
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGGED_IN,false);
    }

    public void setLogin(boolean val){
        editor.putBoolean(IS_LOGGED_IN,val);
        Log.d(TAG,"Login Session Modified");
        editor.commit();
    }

    public boolean isWaiting(){
        return pref.getBoolean(IS_WAITING_FOR_SMS,false);
    }

    public void setWaiting(boolean val){
        editor.putBoolean(IS_WAITING_FOR_SMS,val);
        Log.d(TAG,"Waiting for Sms");
        editor.commit();
    }

    public void createLogin(String name,String email,String mobile){
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_MOBILE,mobile);
        editor.putBoolean(IS_LOGGED_IN,true);
        Log.d(TAG,"Login Session Modified");
        editor.commit();
    }

    public HashMap<String,String> getUserDetails(){
        HashMap<String,String> profile = new HashMap<>();
        profile.put("name",pref.getString(KEY_NAME,null));
        profile.put("email",pref.getString(KEY_EMAIL,null));
        profile.put("mobile",pref.getString(KEY_MOBILE,null));
        return profile;
    }
}
