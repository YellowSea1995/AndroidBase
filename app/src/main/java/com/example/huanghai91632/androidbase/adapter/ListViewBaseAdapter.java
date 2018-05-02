package com.example.huanghai91632.androidbase.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huanghai91632.androidbase.R;
import com.example.huanghai91632.androidbase.model.Item;

import java.util.List;

/**
 * ListView适配器
 */

public class ListViewBaseAdapter extends BaseAdapter {

    private static final String TAG = "ListViewBaseAdapter";
    
    private List<Item> mList;
    private LayoutInflater mInflater;

    public ListViewBaseAdapter(Context context, List<Item> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.item, null);
            viewHolder.imageView = view.findViewById(R.id.iv_image);
            viewHolder.title = view.findViewById(R.id.tv_title);
            viewHolder.content = view.findViewById(R.id.tv_content);
            viewHolder.checked = view.findViewById(R.id.checked);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
    }
    Item item = mList.get(i);
        viewHolder.imageView.setImageResource(item.itemImageResId);
        viewHolder.title.setText(item.itemTitle);
        viewHolder.content.setText(item.itemContent);
        viewHolder.checked.setChecked(mList.get(i).getItemChecked());
        viewHolder.checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.checked.isChecked()) {
                    mList.get(i).setItemChecked(true);
                } else {
                    mList.get(i).setItemChecked(false);
                }

                Log.d(TAG, "onClick: " + "cccc");
            }
        });
        return view;
    }

    public class ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView content;
        public CheckBox checked;
    }
}
