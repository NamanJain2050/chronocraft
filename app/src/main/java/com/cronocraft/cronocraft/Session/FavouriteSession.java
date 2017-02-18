package com.cronocraft.cronocraft.Session;

import android.content.Context;
import android.content.SharedPreferences;

import com.cronocraft.cronocraft.model.FavouriteItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by naman on 28-11-2016.
 */

public class FavouriteSession {

    public static String TAG = FavouriteSession.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "cronocraft-favs";
    public static final String FAVORITES = "FAVOURITE_LIST";

    public FavouriteSession(Context c){
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
        List<FavouriteItem> favorites = getFavorites();
        if (favorites == null)
            favorites = new ArrayList<FavouriteItem>();
        saveWatches(favorites);
    }

    public void saveWatches(List<FavouriteItem> items) {

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(items);
        editor.putString(FAVORITES, jsonFavorites);
        editor.commit();
    }

    public void addWatch(FavouriteItem watch) {
        List<FavouriteItem> favorites = getFavorites();
        if (favorites == null)
            favorites = new ArrayList<FavouriteItem>();
        favorites.add(watch);
        Set<FavouriteItem> hs = new HashSet<>();
        hs.addAll(favorites);
        favorites.clear();
        favorites.addAll(hs);
        saveWatches(favorites);
    }

    public ArrayList<FavouriteItem> getFavorites() {
        List<FavouriteItem> favorites;

        if (pref.contains(FAVORITES)) {
            String jsonFavorites = pref.getString(FAVORITES, null);
            Gson gson = new Gson();
            FavouriteItem[] favoriteItems = gson.fromJson(jsonFavorites,
                    FavouriteItem[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<FavouriteItem>(favorites);
        } else
            return null;

        return (ArrayList<FavouriteItem>) favorites;
    }

    public void removeFavItem(FavouriteItem f){
        ArrayList<FavouriteItem> tmp = getFavorites();
        tmp.remove(f);
        saveWatches(tmp);
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }

}
