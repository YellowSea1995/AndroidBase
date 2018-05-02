package com.example.huanghai91632.androidbase.ui.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huanghai91632.androidbase.R;

/**
 * Fragment中的自定义AlertDialog使用setView()会出错：java.lang.IllegalStateException:
 * The specified child already has a parent. You must call removeView() on the child's parent first.
 */
public class AlertDialogFragment extends Fragment {

    private Button clickBtn;
    private View dialogView;
    private EditText time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alert_dialog, container, false);

        clickBtn = view.findViewById(R.id.clickBtn);

        initUI();

        return view;
    }

    private void initUI() {
        dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.alter_dialog, null);
        time = dialogView.findViewById(R.id.time);
        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(dialogView);
                builder.setTitle("安装确认");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), time.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                 });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }
        });
    }

}
