package com.cronocraft.cronocraft.model;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by naman on 27-11-2016.
 */

public class CartItem {

    private HashMap<String,String> names;
    private HashMap<String,Integer> preview;
    private int cost;
    private String code;

    public CartItem(){

    }

    public CartItem(HashMap<String, String> names,HashMap<String, Integer> preview, int cost) {
        this.names = names;
        this.preview = preview;
        this.cost = cost;
    }

    public HashMap<String, Integer> getPreview() {
        return this.preview;
    }

    public void setPreview(HashMap<String, Integer> preview) {
        this.preview = preview;
    }


    public HashMap<String,String> getNames(){
        return this.names;
    }

    public int getCost(){
        return this.cost;
    }

    public void setNames(HashMap<String,String> names){
        this.names = names;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public int hashCode(){
        //Log.d("In hashcode",this.preview.toString());
        Log.d("In hashcode","hashcode");
        int hashcode = 0;
        hashcode += getPreview().hashCode();
        return hashcode;
    }

    public boolean equals(Object obj){
        Log.d("In equals","in equals");
        if (obj instanceof CartItem) {
            CartItem pp = (CartItem) obj;
            return (pp.preview.equals(this.preview));
        } else {
            return false;
        }
    }

    public String toString() {
        return "imgUrls: " + preview + "   cost: " + cost + "   names:" + names;
    }
}
