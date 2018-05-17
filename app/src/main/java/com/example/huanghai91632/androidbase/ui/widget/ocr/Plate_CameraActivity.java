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

import com.ym.cc.plate.controler.CameraManager;
import com.ym.cc.plate.controler.OcrConstant;
import com.ym.cc.plate.controler.OcrManager;
import com.ym.cc.plate.vo.PlateInfo;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * ��Ƶʶ����
 * @author fangcm 2013-03-18
 *
 */

public class Plate_CameraActivity extends Activity implements SurfaceHolder.Callback{



	private final String TAG = "cc_smart";
	
	private SurfaceView sv_preview;
	private SurfaceHolder surfaceHolder;
	private CameraManager cameraManager;
	private boolean autoFoucs = true;
	private Plate_ViewfinderView finderView;
	private OcrManager ocrManager ;
	private Rect rect;
	private boolean cameraError = false;
	
	private Button btnFlash, btnCancel;
	
	int camera_sv,plate_camera_finderView,bt_cancel,bt_flash,flash_on_s,flash_off_s;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		int main = getResources().getIdentifier("plate_camera", "layout", getPackageName());
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
		
//		int pWidth = cameraManager.getPreviewWidth();
//		int pHeight = cameraManager.getPreviewHeight();
		int pHeight = cameraManager.getPreviewWidth();
		int pWidth = cameraManager.getPreviewHeight();
		
		
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
		surfaceHolder.addCallback(Plate_CameraActivity.this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		finderView.initFinder(tempWidth, tempHeidht, mHandler);
	}
	
	
	
	
	private void initView(){
		
		camera_sv=getResources().getIdentifier("plate_camera_sv", "id", getPackageName());
		plate_camera_finderView=getResources().getIdentifier("plate_camera_finderView", "id", getPackageName());
		bt_cancel=getResources().getIdentifier("plate_bt_cancel", "id", getPackageName());
		bt_flash=getResources().getIdentifier("plate_bt_flash", "id", getPackageName());
		flash_on_s=getResources().getIdentifier("flash_on_s", "drawable", getPackageName());
		flash_off_s=getResources().getIdentifier("flash_off_s", "drawable", getPackageName());
		
		sv_preview = (SurfaceView) findViewById(camera_sv);
		finderView = (Plate_ViewfinderView) findViewById(plate_camera_finderView);
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
					ocrManager = new OcrManager(mHandler,Plate_CameraActivity.this);
					rect = cameraManager.getViewfinderP(finderView.getFinder());
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
					Toast.makeText(getBaseContext(), "相机出现问题，请重启手机！", Toast.LENGTH_SHORT).show();
					mHandler.sendEmptyMessageDelayed(OcrConstant.START_AUTOFOCUS, 500);
				}
				break;
			case OcrConstant.RECOGN_OK:
				mHandler.removeMessages(OcrConstant.TAKE_PREVIEW_DATA_OK);
				mHandler.removeMessages(OcrConstant.START_AUTOFOCUS);
				
		//		String imgPath = "/sdcard/a1234.jpg";
//				String filePath="/sdcard/sinobest/YMO/Plate/";
				String filePath= Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"sinobest/YMO/Plate/";

				SimpleDateFormat formatter    =   new SimpleDateFormat("yyyyMMddhhmmss");
				Date curDate    =   new Date(System.currentTimeMillis());//获取当前时间
				String str    =    formatter.format(curDate);
				
				String imgPath=filePath+str+".jpg";
				
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
				
				PlateInfo plateInfo = ocrManager.getResult(imgPath);
				Intent data2 = new Intent();
				data2.putExtra("plateInfo", plateInfo);
				data2.putExtra("imgPath", imgPath);
				setResult(190, data2);
				
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
				Toast.makeText(getBaseContext(), "引擎初始化失败！", Toast.LENGTH_LONG).show();
				finish();
				break;
			case OcrConstant.START_AUTOFOCUS:
				if(autoFoucs){
					cameraManager.autoFoucs();
					autoFoucs = false;
					mHandler.sendEmptyMessageDelayed(OcrConstant.START_AUTOFOCUS, 500);
					mHandler.sendEmptyMessageDelayed(OcrConstant.REPEAT_AUTOFOCUS, 2000);
//					finderView.scanInit();
//					mHandler.sendEmptyMessageDelayed(100, 500);
				}else {
					cameraManager.autoFocusAndPreviewCallback();	
				}
				break;
			case OcrConstant.RECOGN_LINE_IN_RECT:
				int restult =  (Integer) msg.obj;
				finderView.setLineRect(restult);
				break;
			case 100:
				finderView.scan();
				mHandler.sendEmptyMessageDelayed(100, 8);
				break;
			case 101:
				cameraManager.autoFocusAndPreviewCallback();	
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
		mHandler.sendEmptyMessageDelayed(OcrConstant.START_AUTOFOCUS, 100);
		mHandler.sendEmptyMessageDelayed(101, 5);
		
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
		
		if(ocrManager != null){
//			ocrManager.closeEngine();
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
