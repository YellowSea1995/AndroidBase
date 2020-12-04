package com.example.huanghai91632.androidbase.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
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
import com.example.huanghai91632.androidbase.ui.fragment.HandlerFragment;
import com.example.huanghai91632.androidbase.ui.fragment.HomeFragment;
import com.example.huanghai91632.androidbase.ui.fragment.ListViewFragment;
import com.example.huanghai91632.androidbase.ui.fragment.OcrFragment;
import com.example.huanghai91632.androidbase.ui.fragment.OwnerFragment;
import com.example.huanghai91632.androidbase.ui.fragment.ShoppingCarFragment;
import com.example.huanghai91632.androidbase.ui.fragment.VisibilityFragment;
import com.example.huanghai91632.androidbase.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面Activity
 */
public class HomeActivity extends FragmentActivity {

    private TabLayout tabLayout;
    private List<String> titles;
    private List<Fragment> fragments;
    private FragmentViewPagerAdapter adapter;

    private ViewPager viewPager;
    private int[] tabIcons = {
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

    final String[] GPS_PERMISSIONS = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};

    private static final int BAIDU_READ_PHONE_STATE = 100;//定位权限请求
    private static final int PRIVATE_CODE = 1315;//开启GPS权限

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initFragment();

//        Tools.getTools().getGrantByUser(this, GPS_PERMISSIONS);

        getGps();
    }


    private void getGps() {
        LocationManager locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        boolean isOpenGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isOpenGps) {//开了定位服务
            if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {// 没有权限，申请权限。
                    ActivityCompat.requestPermissions(this, GPS_PERMISSIONS, BAIDU_READ_PHONE_STATE);
                }
            }
        } else {
            Toast.makeText(this, "系统检测到未开启GPS定位服务,请开启", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, PRIVATE_CODE);
        }
    }

    private void initFragment() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        VisibilityFragment visibilityFragment = new VisibilityFragment();
        ShoppingCarFragment shoppingCarFragment = new ShoppingCarFragment();
//        OwnerFragment ownerFragment = new OwnerFragment();
        ListViewFragment listViewFragment = new ListViewFragment();
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        OcrFragment ocrFragment = new OcrFragment();
        HandlerFragment handlerFragment = new HandlerFragment();

        fragments.add(homeFragment);
        fragments.add(listViewFragment);
//        fragments.add(visibilityFragment);
        fragments.add(alertDialogFragment);
//        fragments.add(shoppingCarFragment);
        fragments.add(ocrFragment);
//        fragments.add(ownerFragment);
//        fragments.add(handlerFragment);

        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("发现");
        titles.add("购物车");
        titles.add("我");

        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setUpTabIcons();
        viewPager.setCurrentItem(0);
    }

    public void setUpTabIcons() {
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
