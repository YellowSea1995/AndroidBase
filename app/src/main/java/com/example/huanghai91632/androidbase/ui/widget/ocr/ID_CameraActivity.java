package com.example.huanghai91632.androidbase.ui.widget.ocr;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.yunmai.cc.idcard.controler.CameraManager;
import com.yunmai.cc.idcard.controler.OcrConstant;
import com.yunmai.cc.idcard.controler.OcrManager;
import com.yunmai.cc.idcard.vo.IdCardInfo;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * ��Ƶʶ����
 * @author fangcm 2013-03-18
 *
 */

public class ID_CameraActivity extends Activity implements SurfaceHolder.Callback{



	private final String TAG = "cc_smart";
	
	private SurfaceView sv_preview;
	private SurfaceHolder surfaceHolder;
	private CameraManager cameraManager;
	private boolean autoFoucs = true;
	private ID_ViewfinderView finderView;
	private OcrManager ocrManager ;
	private Rect rect;
	private boolean cameraError = false;
//	private boolean over = false;
	
	private Button btnFlash, btnCancel;
	
	int camera_sv,id_camera_finderView,bt_cancel,bt_flash,flash_on_s,flash_off_s;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		int main = getResources().getIdentifier("id_camera", "layout", getPackageName());
		setContentView(main);
		initView();
		cameraManager = new CameraManager(getBaseContext(),mHandler);
		mCameraOpenThread.start();
		try {
			mCameraOpenThread.join();
			mCameraOpenThread = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cameraError = true;
		}
		if(cameraError){
			 Toast.makeText(getBaseContext(), "照相机未启动！", Toast.LENGTH_SHORT).show();
			 finish();
		}
		setParameters();
		
		
	}
	
	
	
	private Thread mCameraOpenThread = new Thread(new Runnable() {
        public void run() {
        	try {
    			cameraManager.openCamera();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			cameraError = true;
    		}
        }
    });
	
	
	private void setParameters(){
		cameraManager.setCameraFlashModel(Camera.Parameters.FLASH_MODE_OFF);
		cameraManager.setPreviewSize();
		
		int pWidth = cameraManager.getPreviewWidth();
		int pHeight = cameraManager.getPreviewHeight();
		
		
		WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		int wWidth = display.getWidth();
		int wHeight = display.getHeight();
		if(android.os.Build.MANUFACTURER.equals("Lenovo") && android.os.Build.MODEL.equals("IdeaTabS2110AH")){
			wHeight = 800;
		}
		
		
		Log.d(TAG, wWidth+"<--------W----WindowManager-----H------->"+wHeight);
		int tempWidth = pWidth;
		int tempHeidht = pHeight;
		float x = 100.0f;
		int tempW = pWidth;
		int tempH = pHeight;
		if(wWidth > pWidth && wHeight > pHeight){
			while(wWidth > tempW && wHeight > tempH){
				x ++;
				Log.d(TAG, "---xx----->"+x/100.0);
				tempW = (int) (pWidth * x/100.0);
				tempH = (int) (pHeight * x/100.0);
				if(wWidth > tempW && wHeight > tempH){
					tempWidth = tempW;
					tempHeidht = tempH;
				}
			}
			Log.d(TAG,"<------11--wWidth > pWidth && wHeight > pHeight------>");
		}else{
			while(tempWidth > wWidth || tempHeidht > wHeight){
				x --;
				Log.d(TAG, "---xx----->"+x/100.0);
				tempWidth  = (int) (pWidth * x/100.0);
				tempHeidht = (int) (pHeight * x/100.0);
			}
			Log.d(TAG,"<-----22---tempWidth > wWidth || tempHeidht > wHeight------>");
		}
		
		Log.d(TAG, tempWidth+"<--------W----setParameters-----H------->"+tempHeidht);
//		tempWidth = 854;
//		tempHeidht = 480;
		ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) sv_preview.getLayoutParams();
	    lp.width = tempWidth;
	    lp.height = tempHeidht;
	    sv_preview.getHolder().setFixedSize(tempWidth, tempHeidht);
	    sv_preview.setLayoutParams(lp);
		
		surfaceHolder = (SurfaceHolder) sv_preview.getHolder();
		surfaceHolder.addCallback(ID_CameraActivity.this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		finderView.initFinder(tempWidth, tempHeidht, mHandler);
	}
	
	
	
	
	private void initView(){
		
		camera_sv=getResources().getIdentifier("id_camera_sv", "id", getPackageName());
		id_camera_finderView=getResources().getIdentifier("id_camera_finderView", "id", getPackageName());
		bt_cancel=getResources().getIdentifier("id_bt_cancel", "id", getPackageName());
		bt_flash=getResources().getIdentifier("id_bt_flash", "id", getPackageName());
		flash_on_s=getResources().getIdentifier("flash_on_s", "drawable", getPackageName());
		flash_off_s=getResources().getIdentifier("flash_off_s", "drawable", getPackageName());
		
		sv_preview = (SurfaceView) findViewById(camera_sv);
		finderView = (ID_ViewfinderView) findViewById(id_camera_finderView);
		btnCancel = (Button) findViewById(bt_cancel);
		btnFlash = (Button) findViewById(bt_flash);
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult(998);
				finish();
			}
		});
		
		btnFlash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isFlashOn) {
					if (cameraManager.closeFlashlight()) {
						btnFlash.setBackgroundDrawable(getResources().getDrawable(flash_on_s));
						isFlashOn = false;
					}
				} else {
					if (cameraManager.openFlashlight()) {
						btnFlash.setBackgroundDrawable(getResources().getDrawable(flash_off_s));
						isFlashOn = true;
					}
				}
			}
		});
		
	}
	
	private boolean isFlashOn = false;

	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case OcrConstant.TAKE_PREVIEW_DATA_OK:
				 
				if(ocrManager == null){
					ocrManager = new OcrManager(mHandler,ID_CameraActivity.this);
					rect = cameraManager.getViewfinder(finderView.getFinder());
				}
				
				byte[] data_p = (byte[])msg.obj;
				if(data_p != null && data_p.length >0){
//					if(over){
//						return;
//					}
					ocrManager.recognBC(data_p,cameraManager.getPreviewWidth(), cameraManager.getPreviewHeight(),rect);
					mHandler.sendEmptyMessageDelayed(OcrConstant.START_AUTOFOCUS, 100);
				}else{
					finderView.setLineRect(0);
					Toast.makeText(getBaseContext(), "相机出现问题，请重启手机", Toast.LENGTH_SHORT).show();
					mHandler.sendEmptyMessageDelayed(OcrConstant.START_AUTOFOCUS, 500);
				}
				break;
			case OcrConstant.RECOGN_OK:
				mHandler.removeMessages(OcrConstant.TAKE_PREVIEW_DATA_OK);
				mHandler.removeMessages(OcrConstant.START_AUTOFOCUS);
//				String filePath="/sdcard/sinobest/YMO/ID/";
				String filePath= Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"sinobest/YMO/ID/";
				//String imgPath = "/sdcard/sinobest/YMO/aidtest.jpg";
				//String headPath = "/sdcard/sinobest/YMO/aidheadtest.jpg";
				
				SimpleDateFormat formatter    =   new SimpleDateFormat("yyyyMMddhhmmss");
				Date curDate    =   new Date(System.currentTimeMillis());//获取当前时间
				String str    =    formatter.format(curDate);
				
				String imgPath=filePath+str+".jpg";
				String headPath=filePath+"heander"+str+".jpg";
				
				File dir = new File(filePath);
				
				if (!dir.exists()) {
						dir.mkdirs();
					 }
				
				if (dir == null || !dir.exists() || !dir.isDirectory())
		            return;
				 for (File file : dir.listFiles()) {
			            if (file.isFile())
			                file.delete(); // 删除所有文件
			        }
				 
				IdCardInfo idCardInfo = ocrManager.getResult(imgPath,headPath);
				Intent data2 = new Intent();
				data2.putExtra("idcardinfo", idCardInfo);
				data2.putExtra("imgPath", imgPath);
				setResult(200, data2);
				finish();
				
				break;
			case OcrConstant.REPEAT_AUTOFOCUS:
				cameraManager.autoFoucs();
				mHandler.sendEmptyMessageDelayed(OcrConstant.REPEAT_AUTOFOCUS, 2000);
				break;
			case OcrConstant.RECOGN_EG_TIME_OUT:
				Toast.makeText(getBaseContext(), "引擎过期，请尽快更新！", Toast.LENGTH_LONG).show();
				finish();
				break;
			case OcrConstant.RECOGN_EG_LICENSE:
				Toast.makeText(getBaseContext(), "授权失败！", Toast.LENGTH_LONG).show();
				finish();
				break;
			case OcrConstant.RECOGN_EG_INIT_ERROR:
				Toast.makeText(getBaseContext(), "引擎初始化失败", Toast.LENGTH_LONG).show();
				finish();
				break;
			case OcrConstant.START_AUTOFOCUS:
				if(autoFoucs){
					cameraManager.autoFoucs();
					autoFoucs = false;
					mHandler.sendEmptyMessageDelayed(OcrConstant.START_AUTOFOCUS, 500);
					mHandler.sendEmptyMessageDelayed(OcrConstant.REPEAT_AUTOFOCUS, 1500);
				}else {
					cameraManager.autoFocusAndPreviewCallback();	
				}
				break;
			case OcrConstant.RECOGN_LINE_IN_RECT:
				int restult =  (Integer) msg.obj;
				finderView.setLineRect(restult);
				break;
			default:
				cameraManager.initDisplay();
				mHandler.sendEmptyMessageDelayed(OcrConstant.START_AUTOFOCUS, 500);
				Toast.makeText(getBaseContext(), "<>"+msg.what, Toast.LENGTH_SHORT).show();
				break;
			}
		}
		
	};
	
	
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d(TAG, "surfaceCreated");
		if(!cameraManager.cameraOpened()){
			cameraManager.openCamera();
			setParameters();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
		// TODO Auto-generated method stub
		if (holder.getSurface() == null) {
            Log.d(TAG, "holder.getSurface() == null");
            return;
        }
		Log.v(TAG, "surfaceChanged. w=" + width + ". h=" + height);
		surfaceHolder = holder;
		cameraManager.setPreviewDisplay(surfaceHolder);
		cameraManager.initDisplay();
		mHandler.sendEmptyMessageDelayed(OcrConstant.START_AUTOFOCUS, 500);
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d(TAG, "surfaceDestroyed");
		cameraManager.closeCamera();
		surfaceHolder = null;
	}
	
	private void finishAll(){
		if(cameraManager != null){
			cameraManager.closeCamera();
		}
		
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mHandler.removeMessages(OcrConstant.TAKE_PREVIEW_DATA_OK);
		mHandler.removeMessages(OcrConstant.START_AUTOFOCUS);
		finishAll();
		
	}



}
