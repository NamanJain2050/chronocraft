package com.cronocraft.cronocraft.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.cronocraft.cronocraft.helper.LruBitmapCache;
import com.firebase.client.Firebase;
import com.instamojo.android.Instamojo;

import io.smooch.core.Smooch;

/**
 * Created by naman on 06-11-2016.
 */

public class AppController extends Application{

    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static AppController mInstance;
    private ImageLoader mImageLoader;

    private static final String SMOOCH_APP_TOKEN = "3c36fr4phkx3l7h97nfwawfa3";

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
        Smooch.init(this,SMOOCH_APP_TOKEN);
        Firebase.setAndroidContext(this);
        Instamojo.initialize(this);
    }

    public static synchronized AppController getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req,String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }
}
