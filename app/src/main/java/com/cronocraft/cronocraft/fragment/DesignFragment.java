package com.cronocraft.cronocraft.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cronocraft.cronocraft.DesignFragments.CaseFragment;
import com.cronocraft.cronocraft.DesignFragments.DialFragment;
import com.cronocraft.cronocraft.DesignFragments.DialStyleFragment;
import com.cronocraft.cronocraft.DesignFragments.PreviewFragment;
import com.cronocraft.cronocraft.DesignFragments.StrapFragment;
import com.cronocraft.cronocraft.DesignFragments.TopRingFragment;
import com.cronocraft.cronocraft.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DesignFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DesignFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DesignFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public DesignFragment() {
        // Required empty public constructor
    }

    public static DesignFragment newInstance(String param1, String param2) {
        DesignFragment fragment = new DesignFragment();
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


        View view = inflater.inflate(R.layout.fragment_design, container, false);
        //toolbar = (Toolbar) view.findViewById(R.id.toolbar_design);
        //((Activity)getActivity()).setSupportActionBar(toolbar);
        //((Activity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager_design);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs_design);
        tabLayout.setupWithViewPager(viewPager);
        return view;

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFrag(new DialStyleFragment(), "Dial Style");
        adapter.addFrag(new CaseFragment(), "Case");
        adapter.addFrag(new DialFragment(), "Dial");
        adapter.addFrag(new TopRingFragment(), "Top Ring");
        adapter.addFrag(new StrapFragment(), "Strap");
        adapter.addFrag(new PreviewFragment(), "Preview");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
