package com.example.huanghai91632.androidbase.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.huanghai91632.androidbase.R;
import com.example.huanghai91632.androidbase.utils.Tools;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandlerFragment extends Fragment {

    private Button handlerButton;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Tools.toast(getActivity(), "msg.what: " + String.valueOf(msg.what));
            Tools.toast(getActivity(), "handler.getMessageName(msg): " + handler.getMessageName(msg));
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handler, container, false);

        findUI(view);

        buildUI();

        return view;
    }

    private void findUI(View view) {
        handlerButton = view.findViewById(R.id.handlerButton);
    }

    private void buildUI() {
        handlerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendHandlerMessage(handler);
            }
        });
    }

    private void sendHandlerMessage(Handler handler) {
        Message msg = handler.obtainMessage();
        msg.what = 0;
        handler.sendMessage(msg);
    }
}
