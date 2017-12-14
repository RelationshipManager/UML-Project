package com.example.zhang.relationshipManager.fragment;

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

import com.example.zhang.relationshipManager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyRsFragment extends Fragment {

    //Fragment列表
    private ArrayList<Fragment> mFragments;
    //ViewPager
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    //标签栏
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new ArrayList<>();
        mFragments.add(MyRsByTypeFragment.getInstance(1));
        mFragments.add(MyRsByTypeFragment.getInstance(2));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rs, container, false);
        ButterKnife.bind(this, view);
        mViewPager.setAdapter(new FragmentsAdapter(getFragmentManager(), mFragments));
        mTabLayout.setupWithViewPager(mViewPager);

        return view;

    }

    private class FragmentsAdapter extends FragmentPagerAdapter {

        List<Fragment> mFragments;

        FragmentsAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;

        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence title = "";
            switch (position) {
                case 0:
                    title = getText(R.string.friend_rs);
                    break;
                case 1:
                    title = getText(R.string.colleague_rs);
                    break;
            }
            return title;
        }
    }
}
