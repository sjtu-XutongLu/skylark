package com.example.xutong.toolbar.ui.furtherUsage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xutong.toolbar.R;

/**
 * Created by xutong on 2016/5/20.
 */
public class complaint_form extends AppCompatActivity {
    private Toolbar mToolbar;

    private TextView name_textView;
    private TextView location_textView;

    private EditText reason_editText;

    private Button mButton;


    private String name;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_form);

        name_textView = (TextView) findViewById(R.id.name_banned_form);
        location_textView = (TextView) findViewById(R.id.location_banned_form);

        reason_editText = (EditText) findViewById(R.id.complaint_content_form);

        mButton = (Button) findViewById(R.id.complaint_button_form);

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
        location = data.getStringExtra("location");
    }

    private void initView(){
        name_textView.setText("教室名称：" + " " + name);
        location_textView.setText("位置：" + " " + location);
    }

    private void bindButton(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 提交数据
                ProgressDialog progressDialog = new ProgressDialog (complaint_form.this);
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
                        Toast.makeText(complaint_form.this, "action_settings", Toast.LENGTH_SHORT).show();
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
