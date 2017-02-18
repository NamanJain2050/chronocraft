package com.cronocraft.cronocraft.DesignFragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.PreviewSession;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialStyleFragment extends Fragment {

    public static final String DATA_URL = "https://wwwcronocraftcom.000webhostapp.com/fetch_photos/fetch_dial_style.php";
    public static final String TAG_IMAGE_URL = "imgUrl";
    public static final String TAG_ID = "id";

    private GridView gridView;
    private ProgressDialog pDialog;


    private ArrayList<String> images;
    private ArrayList<Integer> ids;

    public DialStyleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_dial_style, container, false);
        ImageView img1 = (ImageView) view.findViewById(R.id.watch_style_1);
        ImageView img2 = (ImageView) view.findViewById(R.id.watch_style_2);
        ImageView img3 = (ImageView) view.findViewById(R.id.watch_style_3);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Style Selected",Toast.LENGTH_SHORT).show();
                PreviewSession session = new PreviewSession(getContext());
                session.setCase(1,"Infinity",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/case_1",null,null),100);
                session.setDial(4,"Paris",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/dial_4",null,null),500);
                session.setStrap(2,"Leather Weaved",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/strap_2",null,null),500);
                session.setTopRing(1,"Attitude",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/top_ring_1",null,null),150);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Style Selected",Toast.LENGTH_SHORT).show();
                PreviewSession session = new PreviewSession(getContext());
                session.setCase(1,"Infinity",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/case_1",null,null),100);
                session.setDial(5,"Prague",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/dial_5",null,null),600);
                session.setStrap(1,"Leather Classic",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/strap_1",null,null),400);
                session.setTopRing(4,"Pure",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/top_ring_4",null,null),200);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Style Selected",Toast.LENGTH_SHORT).show();
                PreviewSession session = new PreviewSession(getContext());
                session.setCase(1,"Infinity",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/case_1",null,null),100);
                session.setDial(2,"Barcelona",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/dial_2",null,null),550);
                session.setStrap(3,"Nato Nylon",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/strap_3",null,null),650);
                session.setTopRing(4,"Pure",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/top_ring_4",null,null),200);
            }
        });
        //gridView = (GridView) view.findViewById(R.id.gridView_dial_style);
        //pDialog = new ProgressDialog(getActivity());
        //images = new ArrayList<>();
        //ids = new ArrayList<>();
        //getData();
        return view;
    }

 /*   private void getData(){

        String tag_json_req = "req_dial_styles";
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(DATA_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
             hideDialog();
                showGrid(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest,tag_json_req);
    }

    private void showGrid(JSONArray jsonArray){
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);
                images.add(obj.getString(TAG_IMAGE_URL));
                ids.add(obj.getInt(TAG_ID));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(),images,ids);
        gridView.setAdapter(gridViewAdapter);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }*/

}
