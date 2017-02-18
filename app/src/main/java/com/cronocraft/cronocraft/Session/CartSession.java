package com.cronocraft.cronocraft.Session;

import android.content.Context;
import android.content.SharedPreferences;

import com.cronocraft.cronocraft.model.CartItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by naman on 27-11-2016.
 */

public class CartSession {

    public static String TAG = CartSession.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "cronocraft-cart";
    public static final String FAVORITES = "Product_List";

    public CartSession(Context c){
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
        List<CartItem> favorites = getFavorites();
        if (favorites == null)
            favorites = new ArrayList<CartItem>();
        saveWatches(favorites);
    }

    public void saveWatches(List<CartItem> items) {

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(items);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addWatch(CartItem watch) {
        List<CartItem> favorites = getFavorites();
        if (favorites == null)
            favorites = new ArrayList<CartItem>();
        favorites.add(watch);
        Set<CartItem> hs = new HashSet<>();
        hs.addAll(favorites);
        favorites.clear();
        favorites.addAll(hs);
        saveWatches(favorites);
    }

    public ArrayList<CartItem> getFavorites() {
        List<CartItem> favorites;

        if (pref.contains(FAVORITES)) {
            String jsonFavorites = pref.getString(FAVORITES, null);
            Gson gson = new Gson();
            CartItem[] favoriteItems = gson.fromJson(jsonFavorites,
                    CartItem[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<CartItem>(favorites);
        } else
            return null;

        return (ArrayList<CartItem>) favorites;
    }

    public void removeCartItem(CartItem f){
        ArrayList<CartItem> tmp = getFavorites();
        tmp.remove(f);
        saveWatches(tmp);
    }

    public int getCartTotal(){
        ArrayList<CartItem> tmp = getFavorites();
        int total = 0;
        for(int i=0;i<tmp.size();i++){
            CartItem c = tmp.get(i);
            total += c.getCost();
        }

        return total;
    }

    public String getCartCode(){
        ArrayList<CartItem> tmp = getFavorites();
        String code = "";
        for(int i=0;i<tmp.size();i++){
            CartItem c = tmp.get(i);
            code += c.getCode();
        }

        return code;
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }
}
