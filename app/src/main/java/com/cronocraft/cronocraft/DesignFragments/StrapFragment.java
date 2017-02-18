package com.cronocraft.cronocraft.DesignFragments;


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
public class StrapFragment extends Fragment {

    public static final String DATA_URL = "https://wwwcronocraftcom.000webhostapp.com/fetch_photos/fetch_strap.php";
    public static final String TAG_IMAGE_URL = "imgUrl";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_PRICE = "price";
    private static final String TAG_PREVIEW = "previewUrl";

    private GridView gridView;
   // private ProgressDialog pDialog;


    private ArrayList<String> images;
    private ArrayList<Integer> ids;
    private ArrayList<String> names;
    private ArrayList<Integer> prices;
    private ArrayList<String> previews;


    public StrapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_strap, container, false);

        final PreviewSession session = new PreviewSession(getContext());
        ImageView img1 = (ImageView) view.findViewById(R.id.strap_1);
        ImageView img2 = (ImageView) view.findViewById(R.id.strap_2);
        ImageView img3 = (ImageView) view.findViewById(R.id.strap_3);
        ImageView img4 = (ImageView) view.findViewById(R.id.strap_4);
        //ImageView img5 = (ImageView) view.findViewById(R.id.strap_5);
        //ImageView img6 = (ImageView) view.findViewById(R.id.strap_6);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Strap Selected",Toast.LENGTH_SHORT).show();
                session.setStrap(1,"Leather Classic",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/strap_1",null,null),400);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Strap Selected",Toast.LENGTH_SHORT).show();
                session.setStrap(2,"Leather Weaved",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/strap_2",null,null),500);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Strap Selected",Toast.LENGTH_SHORT).show();
                session.setStrap(3,"Nato Nylon",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/strap_3",null,null),650);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Strap Selected",Toast.LENGTH_SHORT).show();
                session.setStrap(4,"Metal Mesh",getResources().getIdentifier("com.cronocraft.cronocraft:drawable/strap_4",null,null),750);
            }
        });
        /*gridView = (GridView) view.findViewById(R.id.gridView_strap);
       // pDialog = new ProgressDialog(getActivity());
        images = new ArrayList<>();
        ids = new ArrayList<>();
        names = new ArrayList<>();
        prices = new ArrayList<>();
        previews = new ArrayList<>();
        getData();*/
        return view;
    }

    /*private void getData(){

        String tag_json_req = "req_dial_styles";
       // pDialog.setMessage("Loading...");
        //pDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(DATA_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //hideDialog();
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
                names.add(obj.getString(TAG_NAME));
                prices.add(obj.getInt(TAG_PRICE));
                previews.add(obj.getString(TAG_PREVIEW));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        GridViewAdapterStrap gridViewAdapter = new GridViewAdapterStrap(getContext(),images,ids,names,prices,previews);
        gridView.setAdapter(gridViewAdapter);
    }

   /* private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }*/

}
