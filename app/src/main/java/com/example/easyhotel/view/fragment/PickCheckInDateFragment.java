package com.example.easyhotel.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.FragmentPickCheckInDateBinding;
import com.example.easyhotel.view.CloseCallBack;
import com.example.easyhotel.view.Event;
import com.example.easyhotel.view.activity.MainActivity;
import com.example.easyhotel.viewmodel.MainViewModel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;


public class PickCheckInDateFragment extends Fragment {
    private FragmentPickCheckInDateBinding binding;
    private MainViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pick_check_in_date,container,false);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,6);
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        binding.setMaxdate(calendar.getTimeInMillis());
        binding.setClose(closeCallBack);
        binding.calendarCheckIn.setDate(viewModel.date.getValue().getTime());
        binding.calendarCheckIn.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar1 =  Calendar.getInstance();
                calendar1.set(year,month,dayOfMonth);
                viewModel.set_date(calendar1.getTime());
                getActivity().onBackPressed();
            }
        });
        return binding.getRoot();
    }
    private CloseCallBack closeCallBack = new CloseCallBack() {
        @Override
        public void close() {
            getActivity().onBackPressed();
        }
    };
}
