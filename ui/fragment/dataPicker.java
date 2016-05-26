package com.example.xutong.toolbar.ui.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.xutong.toolbar.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xutong on 2016/5/17.
 */
public class dataPicker extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {
    private static String dataTime;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        dataTime = year+"-"+month+"-"+day;

        MainActivity main = (MainActivity) getActivity();
        main.refreshTimeAndList();
    }
    public static String getDataTime(){
        return dataTime;
    }

    //把字符串转为日期
    public static Date ConvertToDate(String strDate) throws Exception
    {
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(strDate);
    }
}