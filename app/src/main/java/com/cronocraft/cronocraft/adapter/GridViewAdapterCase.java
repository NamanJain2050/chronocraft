//package com.cronocraft.cronocraft.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.Cache;
//import com.android.volley.toolbox.ImageLoader;
//import com.android.volley.toolbox.NetworkImageView;
//import com.cronocraft.cronocraft.R;
//import com.cronocraft.cronocraft.Session.PreviewSession;
//import com.cronocraft.cronocraft.app.AppController;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//
///**
// * Created by naman on 25-11-2016.
// */
//
//public class GridViewAdapterCase extends BaseAdapter{
//
//    private ImageLoader imageLoader;
//    private Context context;
//    private LayoutInflater inflater;
//    private PreviewSession session;
//
//    private ArrayList<String> images;
//    private ArrayList<Integer> ids;
//    private ArrayList<String> names;
//    private ArrayList<Integer> prices;
//    private ArrayList<String> previews;
//
//    public GridViewAdapterCase(Context context,ArrayList<String> images,ArrayList<Integer> ids,ArrayList<String> names,ArrayList<Integer> prices,ArrayList<String> previews){
//        this.context = context;
//        this.images = images;
//        this.ids = ids;
//        this.names = names;
//        this.prices = prices;
//        this.previews = previews;
//    }
//
//    @Override
//    public int getCount(){
//        return images.size();
//    }
//
//    @Override
//    public Object getItem(int position){
//        return images.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent){
//
//        if(inflater == null)
//            inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        if (convertView == null)
//            convertView = inflater.inflate(R.layout.box_case, null);
//
//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//
//        session = new PreviewSession(context);
//
//        NetworkImageView thumbnail = (NetworkImageView) convertView.findViewById(R.id.caseThumbnail);
//        TextView caseName = (TextView) convertView.findViewById(R.id.caseName);
//        TextView casePrice = (TextView) convertView.findViewById(R.id.casePrice);
//
//        //imageLoader = AppController.getInstance().getImageLoader();
//        imageLoader.get(images.get(position),ImageLoader.getImageListener(thumbnail, R.mipmap.company_placeholder,android.R.drawable.ic_dialog_alert));
//
//        thumbnail.setImageUrl(images.get(position),imageLoader);
//        Cache cache = AppController.getInstance().getRequestQueue().getCache();
//        Cache.Entry entry = cache.get(images.get(position));
//        if(entry != null){
//            try{
//                String data = new String(entry.data,"UTF-8");
//            }catch (UnsupportedEncodingException e){
//                e.printStackTrace();
//            }
//        }else{
//
//        }
//        caseName.setText(names.get(position));
//        casePrice.setText("Rs. " + String.valueOf(prices.get(position)));
//
//        thumbnail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"Case Selected: " + (position+1),Toast.LENGTH_SHORT).show();
//                session.setCase(names.get(position),previews.get(position),prices.get(position));
//            }
//        });
//
//        caseName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"Case Selected: " + (position+1),Toast.LENGTH_SHORT).show();
//                session.setCase(names.get(position),previews.get(position),prices.get(position));
//            }
//        });
//
//        casePrice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"Case Selected: " + (position+1),Toast.LENGTH_SHORT).show();
//                session.setCase(names.get(position),previews.get(position),prices.get(position));
//            }
//        });
//
//        return convertView;
//
//    }
//
//}
