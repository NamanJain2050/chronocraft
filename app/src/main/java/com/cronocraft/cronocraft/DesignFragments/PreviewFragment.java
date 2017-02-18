package com.cronocraft.cronocraft.DesignFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.CartSession;
import com.cronocraft.cronocraft.Session.PreviewSession;
import com.cronocraft.cronocraft.activity.CartActivity;
import com.cronocraft.cronocraft.model.CartItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewFragment extends Fragment {

    private PreviewSession session;
    private ImageView previewStrap;
    private ImageView previewCase;
    private ImageView previewDial;
    private ImageView previewTopRing;
    private Button updatePreview,watchCost;



    public PreviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Preview", "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preview, container, false);

        previewStrap = (ImageView) view.findViewById(R.id.previewStrap);
        previewCase = (ImageView) view.findViewById(R.id.previewCase);
        previewDial = (ImageView) view.findViewById(R.id.previewDial);
        previewTopRing = (ImageView) view.findViewById(R.id.previewTopRing);
        updatePreview = (Button) view.findViewById(R.id.updatePreview);
        watchCost = (Button) view.findViewById(R.id.watchCost);
        session = new PreviewSession(getContext());
        showPreview();
        updatePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreview();
            }
        });
        watchCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartSession cart = new CartSession(getContext());
                HashMap<String,Integer> preview = session.getPreviewDetails();
                Log.d("In preview","after preview details");
                HashMap<String,String> names = session.getPreviewNames();
                int cost = session.getCost();
                String code = session.getCode();
                CartItem c = new CartItem();
                c.setCost(cost);
                c.setPreview(preview);
                Log.d("in preview ",c.getPreview().toString());
                c.setNames(names);
                Log.d("In preview",preview.toString());
                c.setCode(code);
                Log.d("In Preview",c.getCode());
                cart.addWatch(c);
                Intent i = new Intent(getContext(), CartActivity.class);
                startActivity(i);
            }
        });
        return view;
    }



    @Override
    public void onPause(){
        super.onPause();
        Log.d("Preview","onPause()");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("Preview","onResume()");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("Preview","onStop()");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.d("Preview","onDestroyView()");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("Preview","onDestroy()");
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Log.d("Preview","onDetach()");
    }

    private void showPreview(){

        HashMap<String,Integer> preview = session.getPreviewDetails();
        ArrayList<Integer> tmp = new ArrayList<>();
        for(String key : preview.keySet()){
            if(preview.get(key) != null)
                tmp.add(preview.get(key));
        }
        Log.d("In Prevuew",tmp.toString());
        /*ImageLoader imageLoader1 = AppController.getInstance().getImageLoader();
        ImageLoader imageLoader2 = AppController.getInstance().getImageLoader();
        ImageLoader imageLoader3 = AppController.getInstance().getImageLoader();
        ImageLoader imageLoader4 = AppController.getInstance().getImageLoader();*/

        /*imageLoader1.get(tmp.get(0), ImageLoader.getImageListener(previewStrap, R.mipmap.company_placeholder, android.R.drawable.ic_dialog_alert));
        imageLoader2.get(tmp.get(1), ImageLoader.getImageListener(previewCase, R.mipmap.company_placeholder, android.R.drawable.ic_dialog_alert));
        imageLoader3.get(tmp.get(2), ImageLoader.getImageListener(previewDial, R.mipmap.company_placeholder, android.R.drawable.ic_dialog_alert));
        imageLoader4.get(tmp.get(3), ImageLoader.getImageListener(previewTopRing, R.mipmap.company_placeholder, android.R.drawable.ic_dialog_alert));*/

        previewStrap.setImageResource(tmp.get(0));
        previewCase.setImageResource(tmp.get(1));
        previewDial.setImageResource(tmp.get(2));
        previewTopRing.setImageResource(tmp.get(3));

       /* Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry1 = cache.get(tmp.get(0));
        Cache.Entry entry2 = cache.get(tmp.get(1));
        Cache.Entry entry3 = cache.get(tmp.get(2));
        Cache.Entry entry4 = cache.get(tmp.get(3));
        if(entry1 != null){
            try{
                String data = new String(entry1.data,"UTF-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }else{

        }
        if(entry2 != null){
            try{
                String data = new String(entry2.data,"UTF-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }else{

        }
        if(entry3 != null){
            try{
                String data = new String(entry3.data,"UTF-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }else{

        }
        if(entry4 != null){
            try{
                String data = new String(entry4.data,"UTF-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }else{

        }*/
        watchCost.setText("Total Cost: " + session.getCost() +"\nAdd To Cart!");
    }

}
