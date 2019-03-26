package com.ncspt.modular.main.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huanghai91632.androidbase.R;
import com.ncspt.adapter.ListViewBaseAdapter;
import com.example.huanghai91632.androidbase.ui.activity.ListViewDetailActivity;
import com.ncspt.modular.main.model.Item;
import com.ncspt.modular.main.view.activity.TestTitleViewDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ListViewFragment
 */
public class TestTitleFragment extends Fragment {

    private static final String TAG = "TestTitleFragment";

    private ListView listView;
    private String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        listView = view.findViewById(R.id.lv_main);

        initUI();

        return view;
    }

    private void initUI() {
        final List<Item> itemList = new ArrayList<>();
        for (int i=2018; i>2013; i--) {
            itemList.add(new Item(
                    R.mipmap.ic_launcher,
                    i + "年11月软考"
            ));
            itemList.add(new Item(
                    R.mipmap.ic_launcher,
                    i + "年5月软考"
            ));
        }

        listView.setAdapter(new ListViewBaseAdapter(getContext(), itemList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                title = ((TextView) view.findViewById(R.id.tv_title)).getText().toString();
                Intent intent = new Intent(getContext(), TestTitleViewDetailActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

    }
}
