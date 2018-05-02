package com.example.huanghai91632.androidbase.study;

import android.app.Activity;
import android.util.Log;

/**
 * Created by Higer on 2018/4/1.
 */

public class KuaiJieJian extends Activity {

    //logt
    private static final String TAG = "KuaiJieJian";

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);

        //logd
        Log.d(TAG, "onChildTitleChanged: ");

        //logn:打印参数
        Log.d(TAG, "onChildTitleChanged() called with: childActivity = [" + childActivity + "], title = [" + title + "]");

        other();
    }

    public void other() {

        /**
         * 自动导包：setting->Editor->General->Auto Import->选中Optimize 和 Add
         */

        //ctrl + W:不断选中代码范围

        //ctrl + alt + space:提示代码

        //ctrl + shift + 上/下:移动代码

        //ctrl + D:复制一行放入下面

        //ctrl + X:剪切

        //ctrl + Y:删除

        //alt + 上/下:跳方法

        //ctrl + shift + N:查找文件

        //ctrl + N:打开类

        //ctrl shift + i:查看方法而不用ctrl + 点击

        //ctrl + alt + 左/右:光标返回

        //alt + 左/右:窗口移动

        //ctrl + +/-:代码折叠

        //alt + 1:隐藏工具栏

        //ctrl + alt + T:选中代码块提示try catch

        //ctrl + J:if、for、foreach、Toast等方法

        //ctrl + R:替换文件

        //ctrl + alt + L:格式化代码
    }
}
