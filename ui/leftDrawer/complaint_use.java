package com.example.xutong.toolbar.ui.leftDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xutong.toolbar.R;
import com.example.xutong.toolbar.model.Application;
import com.example.xutong.toolbar.model.Classroom;
import com.example.xutong.toolbar.ui.furtherUsage.classRoomInfo;
import com.example.xutong.toolbar.ui.furtherUsage.complaint_form;
import com.example.xutong.toolbar.ui.listViewFrag.bannedRoomAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by xutong on 2016/5/17.
 */

public class complaint_use extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView hint;
    private ListView banned_listView;

    private Date date;
    private String hint_text;

    private List<Classroom> banned_classroom_list = new ArrayList<Classroom>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_use);

        date = new Date();
        hint = (TextView) findViewById(R.id.hint_banned);

        banned_listView = (ListView) findViewById(R.id.banned_room);

        initHint();
        initBannedList();
        initToolbar();
    }


    private void initToolbar() {
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
                        Toast.makeText(complaint_use.this, "action_settings", Toast.LENGTH_SHORT).show();
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

    private void initBannedList(){

        Classroom classroom = new Classroom();
        banned_classroom_list.add(classroom);
        Classroom classroom1 = new Classroom();
        classroom1.door = "11";
        banned_classroom_list.add(classroom1);

        banned_listView.setAdapter(new bannedRoomAdapter(this,
                R.layout.banned_room, banned_classroom_list));

        banned_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Classroom tmpClassroom = banned_classroom_list.get(position);
                // show
                Intent bannedRoomInfo = new Intent(complaint_use.this, complaint_form.class);

                bannedRoomInfo.putExtra("name", tmpClassroom.door);

                bannedRoomInfo.putExtra("location", tmpClassroom.building + " " +
                        tmpClassroom.floor);

                startActivity(bannedRoomInfo);
            }
        });

    }

    private void initHint(){
        hint_text = "正在被使用的教室，时间：" + date.toString();
        hint.setText(hint_text);
        // 教室被使用的时间

    }

}