package com.example.xutong.toolbar.ui.furtherUsage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xutong.toolbar.R;

import java.sql.Date;
import java.sql.SQLException;

import static com.example.xutong.toolbar.control.ApplyClassroom.submitBookingLargeClassroomApplication;
import static com.example.xutong.toolbar.control.ApplyClassroom.submitBookingMediumClassroomApplication;
import static com.example.xutong.toolbar.ui.fragment.dataPicker.ConvertToDate;

/**
 * Created by xutong on 2016/5/17.
 */

public class classRoomInfo extends AppCompatActivity {

    private Toolbar mToolbar;

    private TextView name_text;
    private TextView location_text;
    private TextView time_text;


    private EditText topic;
    private EditText specify_topic;
    private EditText person_num;
    private EditText addition;

    private Button mbutton;

    private String name;
    private String time;
    private String location;
    private int getClassroomID;
    private int classroomType;
    private java.util.Date stringDateChanged;
    private String remarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_classroom_info);

        // findView
        name_text = (TextView) findViewById(R.id.nameTag_info);
        location_text = (TextView) findViewById(R.id.location_info);
        time_text = (TextView) findViewById(R.id.applyTime_info);

        topic = (EditText) findViewById(R.id.topic);
        specify_topic = (EditText) findViewById(R.id.specify_topic);
        person_num = (EditText) findViewById(R.id.join_num);
        addition = (EditText) findViewById(R.id.addition);

        mbutton = (Button) findViewById(R.id.apply_classroom);
        // get data in previous step
        // it's better to give data
        getData();

        // initial view
        initView();

        // bind button
        bindButton();

        initToolbar();
    }

    private void getData(){
        Intent data = getIntent();
        name = data.getStringExtra("name");
        time = data.getStringExtra("time");
        location = data.getStringExtra("location");
        getClassroomID = data.getIntExtra("classroomID", 0);
        String dateTmp = data.getStringExtra("applyDate");
        classroomType = data.getIntExtra("type", 0);
        remarks = data.getStringExtra("remarks");
        try {
            stringDateChanged = ConvertToDate(dateTmp);
            Log.d("date", "++"+dateTmp);
            Log.d("date", "+"+stringDateChanged);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView(){
        name_text.setText("教室名称：" + " " + name);
        location_text.setText("位置：" + " " + location);
        time_text.setText("申请时间：" + " " + time);

    }

    private void bindButton(){
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 提交数据
                int userID = 111111;
                int classroomID = getClassroomID;
                Date applyDate = new Date(stringDateChanged.getTime());
                int applyTime = 8;
                Date postTime = new Date(new java.util.Date().getTime());
//
//                int userID;
//                int classroomID;
//                Date applyDate;
//                int applyTime;
//                Date postTime;
//
                String applyReason = specify_topic.getText().toString();
                try {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                            .detectDiskReads().detectDiskWrites().detectNetwork()
                            .penaltyLog().build());

                    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                            .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                            .build());
                    if(classroomType == 1){
                        submitBookingMediumClassroomApplication(userID, classroomID, applyDate, applyTime,
                                postTime);
                    } else {
                        submitBookingLargeClassroomApplication(userID, classroomID, applyDate, applyTime,
                                postTime, applyReason, remarks);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
//                    Log.d("DALTEST", "aa" + e.toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
//                    Log.d("DALTEST", "aaa" + e.toString());
                }

                ProgressDialog progressDialog = new ProgressDialog (classRoomInfo.this);
                progressDialog.setTitle("信息上传中");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                // 返回结果

//                Toast.makeText(classRoomInfo.this, "Success!", Toast.LENGTH_LONG).show();
//                progressDialog.dismiss();
                onBackPressed();

            }
        });
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // toolbar.setLogo(R.drawable.ic_launcher);
        mToolbar.setTitle("教室信息");

        mToolbar.setSubtitle(R.string.name_owner);
        // 标题的文字需在setSupportActionBar之前，不然会无效
        setSupportActionBar(mToolbar);
		/* 这些通过ActionBar来设置也是一样的，注意要在setSupportActionBar(toolbar);之后，不然就报错了 */
        // getSupportActionBar().setTitle("标题");
        // getSupportActionBar().setSubtitle("副标题");
        // getSupportActionBar().setLogo(R.drawable.ic_launcher);

		/* 菜单的监听可以在toolbar里设置，也可以像ActionBar那样，通过下面的两个回调方法来处理 */
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(classRoomInfo.this, "action_settings", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    // not finished task: smart hide
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.apply_root_view:
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                break;
        }
    }
}