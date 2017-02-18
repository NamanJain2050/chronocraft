/*package com.cronocraft.cronocraft.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.cronocraft.cronocraft.Session.PreviewSession;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by naman on 25-11-2016.
 */
/*
public class GridViewAdapter extends BaseAdapter {

    private ImageLoader imageLoader;
    private Context context;
    PreviewSession session;

    private ArrayList<String> images;
    private ArrayList<Integer> ids;

    public GridViewAdapter(Context context,ArrayList<String> images,ArrayList<Integer> ids){
        this.context = context;
        this.images = images;
        this.ids = ids;
    }

    @Override
    public int getCount(){
        return images.size();
    }

    @Override
    public Object getItem(int position){
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        RelativeLayout linearLayout = new RelativeLayout(context);
        //linearLayout.setOrientation(LinearLayout.VERTICAL);

        //NetworkImageView networkImageView = new NetworkImageView(context);

        ImageView img = new ImageView(context);

        session = new PreviewSession(context);

        /*imageLoader = AppController.getInstance().getImageLoader();
        imageLoader.get(images.get(position),ImageLoader.getImageListener(networkImageView, R.mipmap.company_placeholder,android.R.drawable.ic_dialog_alert));

        networkImageView.setImageUrl(images.get(position),imageLoader);
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(images.get(position));
        if(entry != null){
            try{
                String data = new String(entry.data,"UTF-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }else{

        }*/
/*
        Picasso.with(context).load(images.get(position)).resize(500,500).into(img);

        /*networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        networkImageView.setLayoutParams(new GridView.LayoutParams(900,900));*/

  /*      img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Style Selected: " + (position+1),Toast.LENGTH_SHORT).show();

                if(position == 0){
                    session.setCase("Infinity","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/cases/infinity.png",100);
                    session.setDial("Paris","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/dials/paris2.png",500);
                    session.setTopRing("Attitude","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/top_rings/attitude.png",150);
                    session.setStrap("Leather Weaved","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/straps/leather_weaved4.png",500);
                }
                if(position == 1){
                    session.setCase("Infinity","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/cases/infinity.png",100);
                    session.setDial("Prague","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/dials/prague2.png",600);
                    session.setTopRing("Pure","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/top_rings/pure.png",200);
                    session.setStrap("Leather Classic","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/straps/leather_classic3.png",400);
                }
                if(position == 2){

                    session.setCase("Infinity","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/cases/infinity.png",100);
                    session.setDial("Barcelona","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/dials/barcelona3.png",550);
                    session.setTopRing("Pure","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/top_rings/pure.png",200);
                    session.setStrap("Nato Nylon","http://wwwcronocraftcom.000webhostapp.com/images/for_preview/straps/nato_nylon2.png",650);

                }
            }
        });

        linearLayout.addView(img);

        return linearLayout;

    }
}
*/