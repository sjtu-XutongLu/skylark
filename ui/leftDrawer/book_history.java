package com.example.xutong.toolbar.ui.leftDrawer;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import android.widget.ListView;

import android.widget.Toast;

import com.example.xutong.toolbar.R;
import com.example.xutong.toolbar.model.Application;

import com.example.xutong.toolbar.ui.listViewFrag.cancelRoomAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xutong on 2016/5/17.
 */
public class book_history extends AppCompatActivity {
    private Toolbar mToolbar;

    private ListView history_listView;

    private List<Application> history_list = new ArrayList<Application>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel_room_ui);

        history_listView = (ListView) findViewById(R.id.booked_listView_cancel);

        initCancelList();
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
                        Toast.makeText(book_history.this, "action_settings", Toast.LENGTH_SHORT).show();
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

    private void initCancelList(){

        Application application = new Application();
        history_list.add(application);
        Application application1 = new Application();
        history_list.add(application1);

        history_listView.setAdapter(new cancelRoomAdapter(this,
                R.layout.cancel_room_list, history_list));

    }

}