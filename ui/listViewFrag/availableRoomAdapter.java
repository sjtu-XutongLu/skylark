package com.example.xutong.toolbar.ui.listViewFrag;

import android.content.Context;
import android.media.Image;
import android.util.Log;
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
public class availableRoomAdapter extends ArrayAdapter<Classroom> {
    private int resourceId;
    public availableRoomAdapter(Context context, int textViewResourceId,
                         List<Classroom> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Classroom classroom = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        TextView classroomName = (TextView) view.findViewById(R.id.available_room_name);
        TextView classroomSeatNum = (TextView) view.findViewById(R.id.available_seatNum);
        TextView classroomThings = (TextView) view.findViewById(R.id.available_things);

        classroomName.setText(classroom.door);
        classroomSeatNum.setText("座位数：" + classroom.seat);
        classroomThings.setText("设备：" + classroom.remarks);

        return view;
    }
}
