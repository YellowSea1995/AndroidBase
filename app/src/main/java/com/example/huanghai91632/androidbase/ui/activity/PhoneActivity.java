package com.example.huanghai91632.androidbase.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanghai91632.androidbase.R;

/**
 * 手机验证码Activity
 */
public class PhoneActivity extends Activity {

    private Button button_next;
    private EditText edit_phone, edit_code;
    private TextView send;
    private TimeCount time;

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

        setContentView(R.layout.activity_phone);

        init();
    }

    private void init() {
        button_next = findViewById(R.id.button_next);
        edit_phone = findViewById(R.id.edit_phone);
        edit_code = findViewById(R.id.edit_code);
        send = findViewById(R.id.send);

        if (isMobileNO(edit_phone.getText().toString())) {
            send.setEnabled(true);
            send.setTextColor(Color.WHITE);
            send.setBackgroundResource(R.drawable.selector_blue);
        }

        edit_phone.addTextChangedListener(watcher_edit_phone);
        edit_code.addTextChangedListener(watcher_edit_code);

        /**
         * 启用倒计时60秒
         */
        time = new TimeCount(60000, 1000);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requestSmsCode();
                time.start();
            }
        });

//        button_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (button_next != null)
//                {
//                    toast("请输入密码!");
//                }
//            }
//        });
    }

    private TextWatcher watcher_edit_phone = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            if (isMobileNO(edit_phone.getText().toString()))
            {
                send.setEnabled(true);
                send.setTextColor(Color.WHITE);
                send.setBackgroundResource(R.drawable.selector_blue);
//                SharedPreferences sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("username", edit_phone.getText().toString());
//                editor.commit();
            } else {
                send.setEnabled(false);
                send.setBackgroundResource(R.drawable.selector_grey);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
            if (isMobileNO(edit_phone.getText().toString()))
            {
                send.setEnabled(true);
                send.setTextColor(Color.WHITE);
                send.setBackgroundResource(R.drawable.selector_blue);
//                SharedPreferences sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("username", edit_phone.getText().toString());
//                editor.commit();
            } else {
                send.setEnabled(false);
                send.setBackgroundResource(R.drawable.selector_grey);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            if (isMobileNO(edit_phone.getText().toString()))
            {
                send.setEnabled(true);
                send.setTextColor(Color.WHITE);
                send.setBackgroundResource(R.drawable.selector_blue);
//                SharedPreferences sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("username", edit_phone.getText().toString());
//                editor.commit();
            } else {
                send.setEnabled(false);
                send.setBackgroundResource(R.drawable.selector_grey);
            }
        }
    };

    private TextWatcher watcher_edit_code = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            //验证码
            if (isCode(edit_code.getText().toString()))
            {
//                verifyCode();
                if (edit_code.getText().toString().equals("123456"))
                {
                    button_next.setEnabled(true);
                    button_next.setBackgroundResource(R.drawable.selector_blue);
                    button_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (button_next != null)
                            {
                                skipRegisterActivity();
//                                toast("请输入密码!");
//                                setFragments(0);
                            }
                        }
                    });
                }

            } else {
                button_next.setEnabled(false);
                button_next.setBackgroundResource(R.drawable.selector_grey);
            }

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

            //验证码
            if (isCode(edit_code.getText().toString()))
            {
//                verifyCode();
                if (edit_code.getText().toString().equals("123456"))
                {
                    button_next.setEnabled(true);
                    button_next.setBackgroundResource(R.drawable.selector_blue);
                    button_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (button_next != null)
                            {
                                skipRegisterActivity();
//                                toast("请输入密码!");
//                                setFragments(0);
                            }
                        }
                    });
                }
            } else {
                button_next.setEnabled(false);
                button_next.setBackgroundResource(R.drawable.selector_grey);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

            //验证码
            if (isCode(edit_code.getText().toString()))
            {
//                verifyCode();
                if (edit_code.getText().toString().equals("123456"))
                {
                    button_next.setEnabled(true);
                    button_next.setBackgroundResource(R.drawable.selector_blue);
                    button_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (button_next != null)
                            {
                                skipRegisterActivity();
//                                toast("请输入密码!");
//                                Intent intent = new Intent(PhoneActivity.this, RegisterActivity.class);
//                                startActivity(intent);
//                                setFragments(0);
                            }
                        }
                    });
                }
            } else {
                button_next.setEnabled(false);
                button_next.setBackgroundResource(R.drawable.selector_grey);
            }
        }
    };

    public void skipRegisterActivity() {
        toast("请输入密码!");
        Intent intent = new Intent(PhoneActivity.this, RegisterActivity.class);
        intent.putExtra("phone", edit_phone.getText().toString());
        startActivity(intent);
        finish();
    }

    public static boolean isCode(String mobiles) {

        String telRegex = "\\d{6}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)){
            return false;
        }
        else return mobiles.matches(telRegex);
    }

    public static boolean isMobileNO(String mobiles) {

        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)){
            return false;
        }
        else return mobiles.matches(telRegex);
    }

    public void toast(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long millisUntilFinished) {
            send.setClickable(false); //设置不可点击
            send.setText("重新发送(" + millisUntilFinished / 1000 + ")");  //设置倒计时时间
            send.setBackgroundResource(R.drawable.selector_grey); //设置按钮为灰色，这时是不能点击的

            /**
             * 超链接 URLSpan
             * 文字背景颜色 BackgroundColorSpan
             * 文字颜色 ForegroundColorSpan
             * 字体大小 AbsoluteSizeSpan
             * 粗体、斜体 StyleSpan
             * 删除线 StrikethroughSpan
             * 下划线 UnderlineSpan
             * 图片 ImageSpan
             * http://blog.csdn.net/ah200614435/article/details/7914459
             */
            SpannableString spannableString = new SpannableString(send.getText().toString());  //获取按钮上的文字
            ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
            /**
             * public void setSpan(Object what, int start, int end, int flags) {
             * 主要是start跟end，start是起始位置,无论中英文，都算一个。
             * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
             */
            spannableString.setSpan(span, 5, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
            send.setText(spannableString);
        }

        @Override
        public void onFinish() {
            send.setText("重新发送");
            send.setClickable(true);//重新获得点击
            send.setBackgroundResource(R.drawable.selector_blue);  //还原背景色
        }
    }
}
