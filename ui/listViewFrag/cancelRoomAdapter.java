package com.example.xutong.toolbar.ui.listViewFrag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.xutong.toolbar.R;
import com.example.xutong.toolbar.model.Application;


import java.util.List;

/**
 * Created by xutong on 2016/5/21.
 */
public class cancelRoomAdapter extends ArrayAdapter<Application> {
    private int resourceId;
    public cancelRoomAdapter(Context context, int textViewResourceId,
                             List<Application> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Application application = getItem(position);
        final View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        TextView applySubmitTime = (TextView) view.findViewById(R.id.submit_time_cancel_list);
        TextView classNameCancel = (TextView) view.findViewById(R.id.classroom_name_cancel_list);
        TextView applyTime = (TextView) view.findViewById(R.id.use_time_cancel_list);
        TextView topic = (TextView) view.findViewById(R.id.apply_topic_cancel_list);
        TextView success = (TextView) view.findViewById(R.id.cancel_applyTrue_list);

        applySubmitTime.setText("submit_time");
        classNameCancel.setText("name");
        applyTime.setText("duration");
        topic.setText("topic");
        success.setText("success");

        Button button = (Button) view.findViewById(R.id.cancel_list_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DON'T KNOW
                Toast.makeText(view.getContext(), "CANCEL_OK", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
