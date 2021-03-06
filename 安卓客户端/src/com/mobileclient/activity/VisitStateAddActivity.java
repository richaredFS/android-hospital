package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.mobileclient.util.HttpUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.domain.VisitState;
import com.mobileclient.service.VisitStateService;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.Toast;
public class VisitStateAddActivity extends Activity {
	// 声明确定添加按钮
	private Button btnAdd;
	// 声明出诊状态输入框
	private EditText ET_visitStateName;
	protected String carmera_path;
	/*要保存的出诊状态信息*/
	VisitState visitState = new VisitState();
	/*出诊状态管理业务逻辑层*/
	private VisitStateService visitStateService = new VisitStateService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-添加出诊状态");
		// 设置当前Activity界面布局
		setContentView(R.layout.visitstate_add); 
		ET_visitStateName = (EditText) findViewById(R.id.ET_visitStateName);
		btnAdd = (Button) findViewById(R.id.BtnAdd);
		/*单击添加出诊状态按钮*/
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*验证获取出诊状态*/ 
					if(ET_visitStateName.getText().toString().equals("")) {
						Toast.makeText(VisitStateAddActivity.this, "出诊状态输入不能为空!", Toast.LENGTH_LONG).show();
						ET_visitStateName.setFocusable(true);
						ET_visitStateName.requestFocus();
						return;	
					}
					visitState.setVisitStateName(ET_visitStateName.getText().toString());
					/*调用业务逻辑层上传出诊状态信息*/
					VisitStateAddActivity.this.setTitle("正在上传出诊状态信息，稍等...");
					String result = visitStateService.AddVisitState(visitState);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					/*操作完成后返回到出诊状态管理界面*/ 
					Intent intent = new Intent();
					intent.setClass(VisitStateAddActivity.this, VisitStateListActivity.class);
					startActivity(intent); 
					VisitStateAddActivity.this.finish();
				} catch (Exception e) {}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
