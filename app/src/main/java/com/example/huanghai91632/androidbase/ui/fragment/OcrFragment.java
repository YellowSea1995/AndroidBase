package com.example.huanghai91632.androidbase.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.huanghai91632.androidbase.R;
import com.example.huanghai91632.androidbase.ui.widget.ocr.ID_CameraActivity;
import com.yunmai.cc.idcard.vo.IdCardInfo;

import org.json.JSONObject;

/**
 * Ocr获取身份证件号码
 * 获取摄像头权限时，6.0以上要进行判断
 */
public class OcrFragment extends Fragment {

    private static final int REQUEST_CODE = 113;
    private Button takePhotoBtn;
    private TextView idCarNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ocr, container, false);

        takePhotoBtn = view.findViewById(R.id.takePhotoBtn);
        idCarNum = view.findViewById(R.id.idCarNum);

        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ID_CameraActivity.class);
                intent.putExtra("TYPE", "ID");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == 200) {
            String idcardNumber = "";
            IdCardInfo idCardInfo = (IdCardInfo) data.getSerializableExtra("idcardinfo");
            if(idCardInfo != null){
                try {
                    String res = new String(idCardInfo.getCharInfo(),"gbk");
                    JSONObject object=new JSONObject(res);
                    JSONObject numobject=object.getJSONObject("Num");
                    idcardNumber = numobject.getString("value");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (!TextUtils.isEmpty(idcardNumber)) {
                idcardNumber = idcardNumber.replaceAll(" ", "");
                idcardNumber = idcardNumber.replace("(wrong number)", "");
                idCarNum.setText(idcardNumber);
            }
        }
    }
}
