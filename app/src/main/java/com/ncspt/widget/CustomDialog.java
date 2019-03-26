package com.ncspt.widget;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.huanghai91632.androidbase.R;

/**
 * Created by huanghai91632 on 2018/5/3.
 * 自定义Dialog
 */

public class CustomDialog extends Dialog {

    // 上下文对象
    Activity activity;
    // 三个控件
    private RadioButton yes;
    private RadioButton no;
    private EditText azTime;
    private Button confirmBtn;
    private Button cancelBtn;
    // 点击事件
    private View.OnClickListener mClickListener;
    // 构造方法

    public CustomDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public CustomDialog(Activity activity, int theme, View.OnClickListener clickListener) {
        super(activity, theme);
        this.activity = activity;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.custom_dialog);

        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);

        // 获取窗口对象
        Window dialogWindow = this.getWindow();

        WindowManager m = activity.getWindowManager();
        // 获取屏幕宽、高用
        Display d = m.getDefaultDisplay();
        // 获取对话框当前的参数值
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        // 宽度设置为屏幕的0.8
        p.width = (int) (d.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        // 根据id在布局中找到控件对象
        azTime = findViewById(R.id.azTime);
        // 为按钮绑定点击事件监听器
        azTime.setOnClickListener(mClickListener);
        confirmBtn = findViewById(R.id.confirmBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "jjlsjfs", Toast.LENGTH_SHORT).show();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         * dialog.setCancelable(false);
         dialog弹出后会点击屏幕或物理返回键，dialog不消失
         dialog.setCanceledOnTouchOutside(false);
         dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
         */
        this.setCancelable(true);
    }
}
