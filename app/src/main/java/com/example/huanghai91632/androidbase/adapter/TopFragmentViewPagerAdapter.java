package com.example.huanghai91632.androidbase.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.huanghai91632.androidbase.ui.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 顶部tab适配器
 */
public class TopFragmentViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> titles;
    private List<Fragment> fragments;

    public TopFragmentViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.titles = new ArrayList<>();
        this.fragments = fragments;
    }

    /**
     * 数据列表
     * @param datas
     */
    public void setList(List<String> datas) {
        this.titles.clear();
        this.titles.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String plateName = titles.get(position);
        if (plateName == null) {
            plateName = "";
        } else if (plateName.length() > 15) {
            plateName = plateName.substring(0, 15) + "...";
        }
        return plateName;
    }
}
