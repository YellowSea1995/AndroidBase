package com.ncspt.modular.user.view.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanghai91632.androidbase.R;
import com.example.huanghai91632.androidbase.ui.activity.ForgivePasswordActivity;
import com.example.huanghai91632.androidbase.ui.activity.PhoneActivity;
import com.ncspt.modular.main.view.activity.MainActivity;
import com.ncspt.util.CircleImageView;

/**
 * 登录Activity
 */
public class LoginActivity extends Activity {

    private CircleImageView circleImageView;
    private EditText editUserName;
    private EditText editPassword;
    private Button btnLogin;
    private TextView textForgivePassword;
    private TextView textRegister;
    private TextView textFwtk;
    private CheckBox checkBoxAgree;

    private static final String LOGINNAME = "loginName";

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 隐藏ActionBar
         */
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        setContentView(R.layout.activity_login_n);
        initUI();
    }

    private void initUI() {
        editUserName = findViewById(R.id.editUserName);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textForgivePassword = findViewById(R.id.textForgivePassword);
        textRegister = findViewById(R.id.textRegister);

        editUserName.setText("123");
        editPassword.setText("123");

//        String register_username = getIntent().getStringExtra("register_username");
//        editUserName.setText(register_username);

        SharedPreferences shareData = getSharedPreferences(this.getPackageName()
                + ".login", 0);
        editUserName.setText(shareData.getString(LOGINNAME, null));

        /**
         * 光标后移
         */
        CharSequence loginNameCS = editUserName.getText();
        if (loginNameCS instanceof Spannable) {
            Spannable spanText = (Spannable) loginNameCS;
            Selection.setSelection(spanText, loginNameCS.length());
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        textForgivePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgivePasswordActivity.class);
                i.putExtra("userId", editUserName.getText().toString());
                startActivity(i);
//                finish();
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, PhoneActivity.class);
                startActivity(i);
//                finish();
            }
        });
    }

    private void login() {
        String userName = editUserName.getText().toString();
        String password = editPassword.getText().toString();

//        User user = new User();
//        user = userDao.get("123");
//        user.setUserId("123");
//        user.setPassword("123");
//        userService = new UserService();
//        userService.addUser(user);
//        Log.d("user666", "login: " + "");

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            toast("请输入用户名或密码！");
            return;
        } else if(userName.equals(password)) {
            SharedPreferences.Editor loginEditor = getSharedPreferences(this.getPackageName()
                    + ".login", 0).edit();
            loginEditor.putString(LOGINNAME, userName);
            loginEditor.commit();

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            toast("登录成功！");
        } else {
            toast("用户名或密码密码错误！");
        }
    }

    private void toast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        SharedPreferences loginCache = getSharedPreferences(
                this.getPackageName() + ".login", 0);
        loginCache.edit().putString(LOGINNAME, editUserName.getText().toString()).commit();
        super.onStop();
    }
}
