package com.sunlightfoundation.congressinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

/**
 * Created by ganmanisekar on 11/16/16.
 */
public class BillTabFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x =  inflater.inflate(R.layout.bill_tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.bill_tabs);
        viewPager = (ViewPager) x.findViewById(R.id.bill_viewpager);


        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));



        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });


        
        return x;

    }



    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new Bills();
                case 1 : return new BillsNew();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }



        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Active Bills";
                case 1 :
                    return "New Bills";


            }
            return null;
        }
    }
}
