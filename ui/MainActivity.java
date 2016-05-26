package com.example.xutong.toolbar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xutong.toolbar.R;

import com.example.xutong.toolbar.dal.ApplicationDAL;
import com.example.xutong.toolbar.model.Application;
import com.example.xutong.toolbar.model.Classroom;
import com.example.xutong.toolbar.ui.fragment.dataPicker;
import com.example.xutong.toolbar.ui.furtherUsage.classRoomInfo;
import com.example.xutong.toolbar.ui.leftDrawer.assist_apply;
import com.example.xutong.toolbar.ui.leftDrawer.book_history;
import com.example.xutong.toolbar.ui.leftDrawer.complaint_use;
import com.example.xutong.toolbar.ui.leftDrawer.feed_back;
import com.example.xutong.toolbar.ui.listViewFrag.availableRoomAdapter;
import com.example.xutong.toolbar.widget.PagerSlidingTabStrip;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.xutong.toolbar.control.ManageClassroom.getAllAvailableClassroom;
import static com.example.xutong.toolbar.ui.fragment.dataPicker.ConvertToDate;

public class MainActivity extends AppCompatActivity {
	// ?
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	private Toolbar mToolbar;

	private ListView leftListView;
	private ListView mainListView;

	private TextView dateText;
	private Spinner startSpinner;
	private Spinner endSpinner;

	// global data
	private String timeDate;


	private static final String[] slidingLeftStrs = new String[] {
			"查询教室信息", "加入他人预订", "查询历史预订", "投诉违规使用", "使用信息反馈", "注销并退出"
	};

	// test classroom NO:
	//private Classroom classroom_list[] = new Classroom[] {};

	private List<Classroom> classroomList = new ArrayList<Classroom>();
	private availableRoomAdapter adapter;

//	private String name;
//	private Integer seatNum;
//	private ArrayList<String> facility;
//	private ArrayList<Integer> bookCondition;
//	private Boolean isBigClassroom;

	// test classroom specified info
	private static final String[] classroom_info_main = new String[] {
			"东中院4-204", "120",  "小型教室"
	};
	private static final int[] classroom_info_time = new int[]{
			8, 12, 16
	};
	private static final String[] classroom_info_facility = new String[]{
			"显示器"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// findView
		mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		leftListView = (ListView) findViewById(R.id.slidingListView);
		mainListView = (ListView) findViewById(R.id.mainListView);

		dateText = (TextView) findViewById(R.id.validateDateText);

		startSpinner = (Spinner) findViewById(R.id.start_time_spinner);
		endSpinner = (Spinner) findViewById(R.id.end_time_spinner);

		// test for DAL
//		try {
//			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//					.detectDiskReads().detectDiskWrites().detectNetwork()
//					.penaltyLog().build());
//
//			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//					.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
//					.build());
//			Application res = ApplicationDAL.getApplication(1110);
//			Log.d("DALTEST", "a" + res.adminID);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Log.d("DALTEST", "aa" + e.toString());
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			Log.d("DALTEST", "aaa" + e.toString());
//		}

		// end test


		// toolbar setting
		initToolbar();

		// left sliding setting
		initLeftSliding();

		// data select setting
		// been set in xml
		// initTimeText();

		// spinner setting
		initSpinner();

		// classroom array list initial setting
		initClassList();

		// test init value
		Classroom classroom = new Classroom();
		classroomList.add(classroom);
		Classroom classroom2 = new Classroom();
		classroom2.door = "002";
		classroomList.add(classroom2);
	}



	private void initToolbar() {
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
					Toast.makeText(MainActivity.this, "action_settings", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
				return true;
			}
		});

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
				R.string.drawer_close);
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}


	private void initLeftSliding(){
		// 绑定侧滑栏数据，设置侧滑栏响应

		//得到ListView对象的引用
		/*为ListView设置Adapter来绑定数据*/
		leftListView.setAdapter(new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, slidingLeftStrs));
		leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 0 - "查询教室",
				// 1 - "加入他人预订",
				// 2 - "查询历史预订",
				// 3 - "投诉违规使用",
				// 4 - "反馈",
				// 5 - "注销"
				//Log.d("onItemClick", slidingLeftStrs[position]);
				if (position == 0) {
					Log.d("UIChange", slidingLeftStrs[position]);
					// 0 - "查询教室"
					closeDrawer();
				} else if (position == 1) {
					Log.d("UIChange", slidingLeftStrs[position]);
					// 1 - "加入他人预订",
					Intent assistIntent = new Intent(MainActivity.this, assist_apply.class);
					startActivity(assistIntent);
				} else if (position == 2) {
					Log.d("UIChange", slidingLeftStrs[position]);
					// 2 - "查询历史预订",
					Intent historyIntent = new Intent(MainActivity.this, book_history.class);
					startActivity(historyIntent);
				} else if (position == 3) {
					Log.d("UIChange", slidingLeftStrs[position]);
					// 3 - "投诉违规使用",
					Intent complaintIntent = new Intent(MainActivity.this, complaint_use.class);
					startActivity(complaintIntent);
				} else if (position == 4) {
					Log.d("UIChange", slidingLeftStrs[position]);
					// 4 - "反馈",
					Intent feedback = new Intent(MainActivity.this, feed_back.class);
					startActivity(feedback);
				} else if (position == 5) {
					Log.d("UIChange", slidingLeftStrs[position]);
					// 5 - "注销"
					// clean everything in storage
					finish();
				}
			}
		});
	}


	private void initSpinner(){
		// 设置两个spinner用于选择 时间 座位数，设置侧滑栏响应
		startSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Log.d("timeSpinner", "inSelect");
				if (position == 0) {
					Log.d("timeSpinner", "0");
				}

				// 更新list
				refreshClassroomList(timeDate,
						startSpinner.getSelectedItem().toString(),
						endSpinner.getSelectedItem().toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.d("locSpinner", "Nothing");
			}
		});

		endSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Log.d("seatNumSpinner", "inSelect");
				if (position == 0) {
					Log.d("seatNumSpinner", "0");
				}
				// 更新list
				refreshClassroomList(timeDate,
						startSpinner.getSelectedItem().toString(),
						endSpinner.getSelectedItem().toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.d("seatSpinner", "Nothing");
			}
		});
	}


	private void initClassList() {
		adapter = new availableRoomAdapter(this,
				R.layout.available_room, classroomList);
		mainListView.setAdapter(adapter);

		mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Classroom tmpClassroom = classroomList.get(position);
				// show
				Intent specifiedClassroomInfo = new Intent(MainActivity.this, classRoomInfo.class);
				specifiedClassroomInfo.putExtra("name", tmpClassroom.door);

				specifiedClassroomInfo.putExtra("time", startSpinner.getSelectedItem().toString()
						+ "—" + endSpinner.getSelectedItem().toString());

				specifiedClassroomInfo.putExtra("location", tmpClassroom.building + " " +
						tmpClassroom.floor);

				specifiedClassroomInfo.putExtra("applyDate", timeDate);

				specifiedClassroomInfo.putExtra("classroomID", tmpClassroom.classroomID);

				specifiedClassroomInfo.putExtra("type", tmpClassroom.classroomType);

				specifiedClassroomInfo.putExtra("remarks", tmpClassroom.remarks);

				startActivity(specifiedClassroomInfo);
			}
		});

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		/* ShareActionProvider配置 */
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		 switch (item.getItemId()) {
//			 case R.id.action_settings:
//				 Toast.makeText(MainActivity.this, "action_settings", 0).show();
//				 break;
//			 case R.id.action_share:
//				 Toast.makeText(MainActivity.this, "action_share", 0).show();
//				 break;
//			 default:
//				 break;
//		 }
		return super.onOptionsItemSelected(item);
	}



	// classroom show
	private void refreshClassroomList(String date, String start, String end){
		// submit timeSelected, seatNumSelected, get returned array
		Date mydate = new Date();
		try {
			mydate = ConvertToDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());

			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
					.build());
			classroomList = getAllAvailableClassroom(new java.sql.Date(mydate.getTime()), 0, 24);
			Log.d("DALTEST", "awthata");
		} catch (SQLException e) {
			e.printStackTrace();
			Log.d("DALTEST", "aa" + e.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Log.d("DALTEST", "aaa" + e.toString());
		}



		// reset array
		//adapter.clear();
		// test init value
		Classroom classroom = new Classroom();
		classroomList.add(classroom);
		Classroom classroom2 = new Classroom();
		classroom2.door = "002";
		classroomList.add(classroom2);
		Log.d("DALTEST", "aasdasdasdwq");
		adapter = new availableRoomAdapter(this,
				R.layout.available_room, classroomList);
		adapter.notifyDataSetChanged();

		Log.d("refresh", "refreshed!");
	}


	public void onBackPressed() {

		if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			closeDrawer();
			return;
		} else{
			super.onBackPressed();
		}
	}


	protected void closeDrawer() {
		if (mDrawerLayout == null)
			return;
		mDrawerLayout.closeDrawer(GravityCompat.START);
	}

	public void showDatePickerDialog(View view){
		dataPicker mdatePicker = new dataPicker();
		mdatePicker.show(getFragmentManager(), "datePicker");
	}

	public void refreshTimeAndList(){
		timeDate = dataPicker.getDataTime();

		refreshClassroomList(timeDate ,
				startSpinner.getSelectedItem().toString(),
				endSpinner.getSelectedItem().toString());
		dateText.setText(timeDate);

		Log.d("OnDateSet", timeDate);
	}
}
