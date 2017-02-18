package com.cronocraft.cronocraft.model;

import android.util.Log;

/**
 * Created by naman on 28-11-2016.
 */

public class FavouriteItem {

    String name;
    int imgId;
    int cost;
    int id;

    public FavouriteItem(){

    }

    public FavouriteItem(int imdId,String name,int cost,int id){

        this.imgId = imdId;
        this.name = name;
        this.cost = cost;
        this.id = id;
    }

    public int getImgId(){
        return this.imgId;
    }

    public int getCost(){
        return this.cost;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setImgId(int id){
        this.imgId = id;
    }

    public void setCost(int c){
        this.cost = c;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public int hashCode(){
        Log.d("In hashcode","hashcode");
        int hashcode = 0;
        hashcode = cost*20;
        hashcode += name.hashCode();
        hashcode += imgId;
        return hashcode;
    }

    public boolean equals(Object obj){
        Log.d("In equals","in equals");
        if (obj instanceof FavouriteItem) {
            FavouriteItem pp = (FavouriteItem) obj;
            return (pp.name.equals(this.name) && pp.cost == this.cost && pp.imgId == this.imgId);
        } else {
            return false;
        }
    }

    public String toString() {
        return "name: " + name + "   cost: " + cost + "   img:" + imgId;
    }
}
