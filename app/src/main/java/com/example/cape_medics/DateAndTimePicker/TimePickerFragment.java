package com.example.cape_medics.DateAndTimePicker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{


    public interface TimePickerListener{
        void onTimeSet(TimePicker timePicker, int hour, int minute);
    }

    TimePickerListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (TimePickerListener)context;
        }catch (Exception e)
        {
            throw new ClassCastException(getActivity().toString()+" must implements TimePickerListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mListener.onTimeSet(view,hourOfDay, minute);
    }
}