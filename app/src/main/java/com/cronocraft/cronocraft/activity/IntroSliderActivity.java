package com.cronocraft.cronocraft.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.IntroSliderSession;

public class IntroSliderActivity extends Activity {

    private IntroSliderSession pref;
    private ViewPager viewPager;
    private LinearLayout layoutDots;
    private Button btn_next,btn_skip;
    private int[] layouts;
    private TextView[] dots;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = new IntroSliderSession(this);
        if(!pref.isFirstTimeLaunch()){
            launchHomeScreen();
            finish();
        }

        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_intro_slider);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_skip = (Button) findViewById(R.id.btn_skip);
        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4
        };

        addBottomDots(0);
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int curr = getItem(+1);
                if(curr < layouts.length){
                    viewPager.setCurrentItem(curr);
                }else{
                    launchHomeScreen();
                }
            }
        });
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if(position == layouts.length-1){
                btn_next.setText(getString(R.string.start));
                btn_skip.setVisibility(View.GONE);
            }else{
                btn_next.setText(getString(R.string.next));
                btn_skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter{
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter(){

        }

        @Override
        public Object instantiateItem(ViewGroup container,int position){
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position],container,false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount(){
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view,Object obj){
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container,int position,Object obj){
            View view = (View) obj;
            container.removeView(view);
        }
    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            w.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void addBottomDots(int currPage){
        dots = new TextView[layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        layoutDots.removeAllViews();
        for(int i = 0;i < dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currPage]);
            layoutDots.addView(dots[i]);
        }
        if(dots.length > 0){
            dots[currPage].setTextColor(colorsActive[currPage]);
        }
    }

    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen(){
        pref.setFirstTimeLaunch(false);
        startActivity(new Intent(IntroSliderActivity.this,LoginActivity.class));
        finish();
    }
}
