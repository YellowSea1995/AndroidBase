package com.example.huanghai91632.androidbase.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huanghai91632.androidbase.R;

public class RegisterActivity extends Activity {

    private Button save;
    private EditText register_username, register_password, register_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {
        register_username = findViewById(R.id.register_username);
        register_password = findViewById(R.id.register_password);
        register_confirm_password = findViewById(R.id.register_confirm_password);
        save = findViewById(R.id.rr);

//        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("userId", 0);
//        String userId = sharedPreferences.getString("username", "");
//        register_username.setText(userId);

        String phone = getIntent().getStringExtra("phone");
        register_username.setText(phone);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register_password.getText().toString()
                        .equals(register_confirm_password.getText().toString())) {
                    toast("注册成功！");
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("register_username", register_username.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    toast("密码不一致！");
                }
            }
        });
    }

    private void toast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }
}
