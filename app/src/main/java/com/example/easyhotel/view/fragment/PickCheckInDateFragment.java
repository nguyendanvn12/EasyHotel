package com.example.easyhotel.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.FragmentPickCheckInDateBinding;
import com.example.easyhotel.view.CallBack;
import com.example.easyhotel.view.CheckInDateCallback;
import com.example.easyhotel.viewmodel.MainViewModel;

import java.util.Calendar;


public class PickCheckInDateFragment extends Fragment {
    private FragmentPickCheckInDateBinding binding;
    private long date;
    private CheckInDateCallback callback1;

    public PickCheckInDateFragment(long date, CheckInDateCallback callback1) {
        this.date = date;
        this.callback1 = callback1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pick_check_in_date,container,false);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,6);
        binding.setMaxdate(calendar.getTimeInMillis());
        binding.setClose(callBack);
        binding.calendarCheckIn.setDate(date);
        binding.calendarCheckIn.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar1 =  Calendar.getInstance();
                calendar1.set(year,month,dayOfMonth);
               callback1.pickDate(calendar1.getTimeInMillis());
                getActivity().onBackPressed();
            }
        });
        return binding.getRoot();
    }
    private CallBack callBack = new CallBack() {
        @Override
        public void keywordChanged(CharSequence text) {

        }

        @Override
        public void close() {
            getActivity().onBackPressed();
        }
    };
}
