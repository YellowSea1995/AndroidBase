package com.example.huanghai91632.androidbase.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huanghai91632.androidbase.R;

/**
 * 输入账号Activity
 */
public class ForgivePasswordActivity extends Activity {

    private EditText editUserName;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        /**
//         * 自定义ActionBar
//         */
//        ActionBar actionBar = getActionBar();
//
//        if (actionBar != null) {
//            //Enable自定义的View
//            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//            //设置自定义的布局
//            actionBar.setCustomView(R.layout.account_action_bar);
//        }

        setContentView(R.layout.activity_forgive_password);

        init();
    }

    private void init() {
        editUserName = findViewById(R.id.editUserName);
        btnConfirm = findViewById(R.id.btnConfirm);

        findViewById(R.id.textBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String userId = getIntent().getStringExtra("userId");
        if (!TextUtils.isEmpty(userId)) {
            editUserName.setText(userId);
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("请选择找回密码方式");
//                Intent intent = new Intent(ForgivePasswordActivity.this, VerificationActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void toast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }
}
