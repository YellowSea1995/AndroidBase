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

import com.example.huanghai91632.androidbase.R;
import com.example.huanghai91632.androidbase.adapter.ListViewBaseAdapter;
import com.example.huanghai91632.androidbase.model.Item;
import com.example.huanghai91632.androidbase.ui.activity.ListViewDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ListViewFragment
 */
public class KnowledgeTitleFragment extends Fragment {

    private static final String TAG = "ListViewFragment";

    private ListView listView;
    private CheckBox checked;
    private List<Item> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        listView = view.findViewById(R.id.lv_main);
//        checked = listView.findViewById(R.id.checked);

        initUI();

        return view;
    }

    private void initUI() {
        final List<Item> itemList = new ArrayList<>();
        for (int i=0; i<20; i++) {
            itemList.add(new Item(
                    R.mipmap.ic_launcher,
                    "我是标题" + i,
                    "我是内容" + i,
                    "18814126232",
                    false
            ));
        }

        listView.setAdapter(new ListViewBaseAdapter(getContext(), itemList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ListViewDetailActivity.class);
                startActivity(intent);
            }
        });

    }
}
