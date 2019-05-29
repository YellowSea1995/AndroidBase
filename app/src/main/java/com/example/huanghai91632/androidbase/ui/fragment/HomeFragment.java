package com.example.huanghai91632.androidbase.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huanghai91632.androidbase.R;
import com.example.huanghai91632.androidbase.adapter.TopFragmentViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private List<String> titles;
    private List<Fragment> fragments;
    private ViewPager viewPager;
    private TopFragmentViewPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        initFragment(view);

        initTitle();

        adapter = new TopFragmentViewPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        // 更新适配器数据
        adapter.setList(titles);

        return view;
    }

    private void initFragment(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        fragments = new ArrayList<>();
        VisibilityFragment visibilityFragment = new VisibilityFragment();
        ShoppingCarFragment shoppingCarFragment = new ShoppingCarFragment();
        OwnerFragment ownerFragment = new OwnerFragment();
        ListViewFragment listViewFragment = new ListViewFragment();
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        OcrFragment ocrFragment = new OcrFragment();
        HandlerFragment handlerFragment = new HandlerFragment();

        fragments.add(listViewFragment);
        fragments.add(visibilityFragment);
        fragments.add(alertDialogFragment);
        fragments.add(shoppingCarFragment);
        fragments.add(ocrFragment);
        fragments.add(ownerFragment);
        fragments.add(handlerFragment);

        initTitle();

        adapter = new TopFragmentViewPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        // 更新适配器数据
        adapter.setList(titles);
    }

    private void initTitle() {
        titles = new ArrayList<>();
        titles.add("关注");
        titles.add("推荐");
        titles.add("热点");
        titles.add("视频");
        titles.add("小说");
        titles.add("娱乐");
        titles.add("问答");
//        titles.add("图片");
//        titles.add("科技");
//        titles.add("懂车帝");
//        titles.add("体育");
//        titles.add("财经");
//        titles.add("军事");
//        titles.add("国际");
//        titles.add("健康");
    }

}
