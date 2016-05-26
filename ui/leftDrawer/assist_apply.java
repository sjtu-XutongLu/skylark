package com.example.xutong.toolbar.ui.leftDrawer;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xutong.toolbar.R;

import java.sql.Date;
import java.sql.SQLException;

import static com.example.xutong.toolbar.control.ApplyClassroom.submitJoinApplication;

/**
 * Created by xutong on 2016/5/16.
 */
public class assist_apply extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button mButton;
    private EditText assist_no;
    private EditText assist_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assist_apply);

        assist_no = (EditText) findViewById(R.id.apply_no);
        assist_code = (EditText) findViewById(R.id.apply_code);

        mButton = (Button) findViewById(R.id.assist_apply_submit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // submit to controller

                int userID = 111111;

                String no = assist_no.getText().toString();
                String code = assist_code.getText().toString();
                int applyNumber = Integer.parseInt(no);
                int applyPassword = Integer.parseInt(code);

                try {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                            .detectDiskReads().detectDiskWrites().detectNetwork()
                            .penaltyLog().build());

                    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                            .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                            .build());
                    submitJoinApplication(userID, applyNumber, applyPassword);
//                    Log.d("DALTEST", "a" + res.adminID);
                } catch (SQLException e) {
                    e.printStackTrace();
//                    Log.d("DALTEST", "aa" + e.toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
//                    Log.d("DALTEST", "aaa" + e.toString());
                }


                // get return value
                //String returnValue =

                // show result
                Toast.makeText(assist_apply.this,"assist_apply_OK",Toast.LENGTH_SHORT).show();
            }
        });
        initViews();
    }


    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // toolbar.setLogo(R.drawable.ic_launcher);
        mToolbar.setTitle(R.string.name_function);

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
                        Toast.makeText(assist_apply.this, "action_settings", Toast.LENGTH_SHORT).show();
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

}