package com.ncspt.modular.main.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.huanghai91632.androidbase.R;

public class TestTitleViewDetailActivity extends Activity {

    private TextView testContent;
    private Bundle data;
    private String testPaper1 = "1";
    private String testPaper2 = "2";
    private String testPaper3 = "1";
    private String testPaper4 = "1";
    private String testPaper5 = "1";
    private String testPaper6 = "1";
    private String testPaper7 = "1";
    private String testPaper8 = "1";
    private String testPaper9 = "1";
    private String testPaper10 = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_title_detail);

        initUI();
    }

    private void initUI() {
        testContent = findViewById(R.id.testContent);

        data = this.getIntent().getExtras() == null ? new Bundle() : this.getIntent().getExtras();

        switch (data.get("title").toString()) {
            case "2018年11月软考":
                testContent.setText(testPaper1);
                break;
            case "2018年5月软考":
                testContent.setText(testPaper2);
                break;
            case "2017年11月软考":
                testContent.setText(testPaper3);
                break;
            case "2017年5月软考":
                testContent.setText(testPaper4);
                break;
            case "2016年11月软考":
                testContent.setText(testPaper5);
                break;
            case "2016年5月软考":
                testContent.setText(testPaper6);
                break;
            case "2015年11月软考":
                testContent.setText(testPaper7);
                break;
            case "2015年5月软考":
                testContent.setText(testPaper8);
                break;
            case "2014年11月软考":
                testContent.setText(testPaper9);
                break;
            case "2014年5月软考":
                testContent.setText(testPaper10);
                break;
            default:
                break;
        }
    }
}
