package com.example.xutong.toolbar.ui.listViewFrag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.xutong.toolbar.R;
import com.example.xutong.toolbar.model.Classroom;

import java.util.List;

/**
 * Created by xutong on 2016/5/20.
 */
public class bannedRoomAdapter extends ArrayAdapter<Classroom> {
    private int resourceId;
    public bannedRoomAdapter(Context context, int textViewResourceId,
                                List<Classroom> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Classroom classroom = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        TextView bannedName = (TextView) view.findViewById(R.id.classroom_name_banned);
        TextView bannedLocation = (TextView) view.findViewById(R.id.location_banned);
        TextView bannedNum = (TextView) view.findViewById(R.id.seat_num_banned);

        bannedName.setText("教室名称："+classroom.door);
        bannedLocation.setText("教室位置："+classroom.building + classroom.floor);
        bannedNum.setText("教室座位数：" + classroom.seat);

        return view;
    }

}
