package com.cronocraft.cronocraft.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.FavouriteSession;
import com.cronocraft.cronocraft.model.FavouriteItem;
import com.cronocraft.cronocraft.watches.WatchEightActivity;
import com.cronocraft.cronocraft.watches.WatchFiveActivity;
import com.cronocraft.cronocraft.watches.WatchFourActivity;
import com.cronocraft.cronocraft.watches.WatchOneActivity;
import com.cronocraft.cronocraft.watches.WatchSevenActivity;
import com.cronocraft.cronocraft.watches.WatchSixActivity;
import com.cronocraft.cronocraft.watches.WatchThreeActivity;
import com.cronocraft.cronocraft.watches.WatchTwoActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WatchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WatchFragment newInstance(String param1, String param2) {
        WatchFragment fragment = new WatchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_watch, container, false);
        ImageView img1 = (ImageView) v.findViewById(R.id.watches_1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WatchOneActivity.class);
                startActivity(i);
            }
        });

        ImageView img2 = (ImageView) v.findViewById(R.id.watches_2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WatchTwoActivity.class);
                startActivity(i);
            }
        });
        ImageView img3 = (ImageView) v.findViewById(R.id.watches_3);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WatchThreeActivity.class);
                startActivity(i);
            }
        });
        ImageView img4 = (ImageView) v.findViewById(R.id.watches_4);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WatchFourActivity.class);
                startActivity(i);
            }
        });
        ImageView img5 = (ImageView) v.findViewById(R.id.watches_5);
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WatchFiveActivity.class);
                startActivity(i);
            }
        });
        ImageView img6 = (ImageView) v.findViewById(R.id.watches_6);
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WatchSixActivity.class);
                startActivity(i);
            }
        });
        ImageView img7 = (ImageView) v.findViewById(R.id.watches_7);
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WatchSevenActivity.class);
                startActivity(i);
            }
        });
        ImageView img8 = (ImageView) v.findViewById(R.id.watches_8);
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WatchEightActivity.class);
                startActivity(i);
            }
        });
        Button btn1 = (Button) v.findViewById(R.id.watches_1_btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavouriteItem f = new FavouriteItem();
                f.setId(1);
                f.setCost(1500);
                f.setName("Prague 41 MM");
                f.setImgId(getResources().getIdentifier("com.cronocraft.cronocraft:drawable/watch_1",null,null));
                FavouriteSession fav = new FavouriteSession(getContext());
                fav.addWatch(f);
                Toast.makeText(getContext(),"Added to favourites!",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn2 = (Button) v.findViewById(R.id.watches_2_btn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavouriteItem f = new FavouriteItem();
                f.setId(2);
                f.setCost(1500);
                f.setName("Paris 41 MM");
                f.setImgId(getResources().getIdentifier("com.cronocraft.cronocraft:drawable/watch_2",null,null));
                FavouriteSession fav = new FavouriteSession(getContext());
                fav.addWatch(f);
                Toast.makeText(getContext(),"Added to favourites!",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn3 = (Button) v.findViewById(R.id.watches_3_btn);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavouriteItem f = new FavouriteItem();
                f.setId(3);
                f.setCost(1500);
                f.setName("Paris 41 MM");
                f.setImgId(getResources().getIdentifier("com.cronocraft.cronocraft:drawable/watch_3",null,null));
                FavouriteSession fav = new FavouriteSession(getContext());
                fav.addWatch(f);
                Toast.makeText(getContext(),"Added to favourites!",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn4 = (Button) v.findViewById(R.id.watches_4_btn);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavouriteItem f = new FavouriteItem();
                f.setId(4);
                f.setCost(1500);
                f.setName("Barcelona 41 MM");
                f.setImgId(getResources().getIdentifier("com.cronocraft.cronocraft:drawable/watch_4",null,null));
                FavouriteSession fav = new FavouriteSession(getContext());
                fav.addWatch(f);
                Toast.makeText(getContext(),"Added to favourites!",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn5 = (Button) v.findViewById(R.id.watches_5_btn);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavouriteItem f = new FavouriteItem();
                f.setId(5);
                f.setCost(2000);
                f.setName("Prague 41 MM");
                f.setImgId(getResources().getIdentifier("com.cronocraft.cronocraft:drawable/watch_5",null,null));
                FavouriteSession fav = new FavouriteSession(getContext());
                fav.addWatch(f);
                Toast.makeText(getContext(),"Added to favourites!",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn6 = (Button) v.findViewById(R.id.watches_6_btn);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavouriteItem f = new FavouriteItem();
                f.setId(6);
                f.setCost(2000);
                f.setName("Barcelona 41 MM");
                f.setImgId(getResources().getIdentifier("com.cronocraft.cronocraft:drawable/watch_6",null,null));
                FavouriteSession fav = new FavouriteSession(getContext());
                fav.addWatch(f);
                Toast.makeText(getContext(),"Added to favourites!",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn7 = (Button) v.findViewById(R.id.watches_7_btn);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavouriteItem f = new FavouriteItem();
                f.setId(7);
                f.setCost(2500);
                f.setName("Paris 41 MM");
                f.setImgId(getResources().getIdentifier("com.cronocraft.cronocraft:drawable/watch_7",null,null));
                FavouriteSession fav = new FavouriteSession(getContext());
                fav.addWatch(f);
                Toast.makeText(getContext(),"Added to favourites!",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn8 = (Button) v.findViewById(R.id.watches_8_btn);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavouriteItem f = new FavouriteItem();
                f.setId(8);
                f.setCost(2500);
                f.setName("Prague 41 MM");
                f.setImgId(getResources().getIdentifier("com.cronocraft.cronocraft:drawable/watch_8",null,null));
                FavouriteSession fav = new FavouriteSession(getContext());
                fav.addWatch(f);
                Toast.makeText(getContext(),"Added to favourites!",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
