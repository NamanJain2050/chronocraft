package com.cronocraft.cronocraft.Session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by naman on 06-11-2016.
 */

public class IntroSliderSession {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "cronocraft-intro-slider";
    private static final String IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";

    public IntroSliderSession(Context c){
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean val){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH,val);
        editor.commit();
    }

    public boolean isFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }
}
