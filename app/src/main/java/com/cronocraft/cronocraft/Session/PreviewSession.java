package com.cronocraft.cronocraft.Session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by naman on 26-11-2016.
 */

public class PreviewSession{

    public static String TAG = PreviewSession.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "cronocraft-preview";

    private static final String CASE_ID = "caseId";
    private static final String CASE_NAME = "caseName";
    private static final String CASE_PRICE = "casePrice";
    private static final String CASE_NUMBER = "caseNumber";

    private static final String DIAL_ID = "dialId";
    private static final String DIAL_NAME = "dialName";
    private static final String DIAL_PRICE = "dialPrice";
    private static final String DIAL_NUMBER = "dialNumber";

    private static final String TOP_RING_ID = "topRingId";
    private static final String TOP_RING_NAME = "topRingName";
    private static final String TOP_RING_PRICE = "topRingPrice";
    private static final String TOP_RING_NUMBER = "topRingNumber";

    private static final String STRAP_ID = "strapId";
    private static final String STRAP_NAME = "strapName";
    private static final String STRAP_PRICE = "strapPrice";
    private static final String STRAP_NUMBER = "strapNumber";

    private int defStrap,defCase,defDial,defTopRing;


    public PreviewSession(Context c){
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        defStrap = this.context.getResources().getIdentifier("com.cronocraft.cronocraft:drawable/default_strap",null,null);
        defCase = this.context.getResources().getIdentifier("com.cronocraft.cronocraft:drawable/default_case",null,null);
        defDial = this.context.getResources().getIdentifier("com.cronocraft.cronocraft:drawable/default_dial",null,null);
        defTopRing = this.context.getResources().getIdentifier("com.cronocraft.cronocraft:drawable/default_top_ring",null,null);
        editor = pref.edit();
    }

    public void setCase(int a,String name,int id,int price){
        editor.putInt(CASE_NUMBER,a);
        editor.putString(CASE_NAME,name);
        editor.putInt(CASE_ID,id);
        editor.putInt(CASE_PRICE,price);
        editor.commit();
    }

//    public void setDial(String name,int id,int price){
//        editor.putString(DIAL_NAME,name);
//        editor.putInt(DIAL_ID,id);
//        editor.putInt(DIAL_PRICE,price);
//        editor.commit();
//    }

    public void setDial(int a,String name,int id,int price){
        editor.putInt(DIAL_NUMBER,a);
        editor.putString(DIAL_NAME,name);
        editor.putInt(DIAL_ID,id);
        editor.putInt(DIAL_PRICE,price);
        editor.commit();
    }

    public void setTopRing(int a,String name,int id,int price){
        editor.putInt(TOP_RING_NUMBER,a);
        editor.putString(TOP_RING_NAME,name);
        editor.putInt(TOP_RING_ID,id);
        editor.putInt(TOP_RING_PRICE,price);
        editor.commit();
    }

    public void setStrap(int a,String name,int id,int price){
        editor.putInt(STRAP_NUMBER,a);
        editor.putString(STRAP_NAME,name);
        editor.putInt(STRAP_ID,id);
        editor.putInt(STRAP_PRICE,price);
        editor.commit();
    }

    public HashMap<String,Integer> getPreviewDetails(){
        HashMap<String,Integer> preview = new HashMap<>();
        preview.put("case",pref.getInt(CASE_ID,defCase));
        preview.put("dial",pref.getInt(DIAL_ID,defDial));
        preview.put("topRing",pref.getInt(TOP_RING_ID,defTopRing));
        preview.put("strap",pref.getInt(STRAP_ID,defStrap));
        return preview;
    }

    public int getCost(){
        return (pref.getInt(CASE_PRICE,0) + pref.getInt(DIAL_PRICE,0) + pref.getInt(TOP_RING_PRICE,0) + pref.getInt(STRAP_PRICE,0));
    }

    public HashMap<String,String> getPreviewNames(){
        HashMap<String,String> names = new HashMap<>();
        names.put("case",pref.getString(CASE_NAME,"not defined"));
        names.put("topRing",pref.getString(TOP_RING_NAME,"not defined"));
        names.put("strap",pref.getString(STRAP_NAME,"not defined"));
        names.put("dial",pref.getString(DIAL_NAME,null));
        return names;
    }

    public String getCode(){
        String code = Integer.toString(pref.getInt(CASE_NUMBER,0)) + Integer.toString(pref.getInt(DIAL_NUMBER,0)) + Integer.toString(pref.getInt(TOP_RING_NUMBER,0)) + Integer.toString(pref.getInt(STRAP_NUMBER,0));
        Log.d("code",code);
        return code;
    }
}
