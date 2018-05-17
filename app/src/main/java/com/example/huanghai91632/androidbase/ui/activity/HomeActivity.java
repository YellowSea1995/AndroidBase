package com.example.huanghai91632.androidbase.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanghai91632.androidbase.R;
import com.example.huanghai91632.androidbase.adapter.FragmentViewPagerAdapter;
import com.example.huanghai91632.androidbase.ui.fragment.AlertDialogFragment;
import com.example.huanghai91632.androidbase.ui.fragment.HomeFragment;
import com.example.huanghai91632.androidbase.ui.fragment.ListViewFragment;
import com.example.huanghai91632.androidbase.ui.fragment.OcrFragment;
import com.example.huanghai91632.androidbase.ui.fragment.OwnerFragment;
import com.example.huanghai91632.androidbase.ui.fragment.ShoppingCarFragment;
import com.example.huanghai91632.androidbase.ui.fragment.VisibilityFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面Activity
 */
public class HomeActivity extends FragmentActivity {

    public TabLayout tabLayout;
    public List<String> titles;
    public List<Fragment> fragments;
    public FragmentManager fm;

    public ViewPager viewPager;
    public int[] tabIcons = {
            R.drawable.tab_home,
            R.drawable.tab_visibility,
            R.drawable.tab_shoppingcar,
            R.drawable.tab_account
    };

    public static boolean isExit = false;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initFragment();
    }

    private void initFragment() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        VisibilityFragment visibilityFragment = new VisibilityFragment();
        ShoppingCarFragment shoppingCarFragment = new ShoppingCarFragment();
        OwnerFragment ownerFragment = new OwnerFragment();
        ListViewFragment listViewFragment = new ListViewFragment();
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        OcrFragment ocrFragment = new OcrFragment();

//        fragments.add(homeFragment);
        fragments.add(listViewFragment);
//        fragments.add(visibilityFragment);
        fragments.add(alertDialogFragment);
//        fragments.add(shoppingCarFragment);
        fragments.add(ocrFragment);
        fragments.add(ownerFragment);

        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("发现");
        titles.add("购物车");
        titles.add("我");

        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        viewPager.setCurrentItem(0);
    }

    public void setupTabIcons() {
        tabLayout.getTabAt(0).setCustomView(getTabView(0));
        tabLayout.getTabAt(1).setCustomView(getTabView(1));
        tabLayout.getTabAt(2).setCustomView(getTabView(2));
        tabLayout.getTabAt(3).setCustomView(getTabView(3));
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.tab_item, null);
        TextView txt_title = view.findViewById(R.id.textTab);
        txt_title.setText(titles.get(position));
        ImageView img_title = view.findViewById(R.id.imgTab);
        img_title.setImageResource(tabIcons[position]);
        return view;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
//            System.exit(0);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
}
