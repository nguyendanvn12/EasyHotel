package com.example.easyhotel.view.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.FragmentChangeFilterBinding;
import com.example.easyhotel.view.CheckInDateCallback;
import com.example.easyhotel.view.DurationCallback;
import com.example.easyhotel.viewmodel.ListHotelFilterViewModel;

import java.util.Date;


public class ChangeFilterFragment extends Fragment implements View.OnClickListener, DurationCallback, CheckInDateCallback {
    private ViewGroup container;
    private View bg;
    private FragmentChangeFilterBinding binding;
    private ListHotelFilterViewModel filterViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.container = container;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_change_filter,container,false);
        initBg();
        binding.setLifecycleOwner(requireActivity());
        filterViewModel = new ViewModelProvider(requireActivity()).get(ListHotelFilterViewModel.class);
        binding.setFilter(filterViewModel);
        binding.linearLayout.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
        binding.lnDuration.setOnClickListener(this);
        binding.background.setOnClickListener(this);
        binding.btnSearch.setOnClickListener(this::onClick);
        return binding.getRoot();
    }

    private void initBg() {
        bg = new View(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bg.setLayoutParams(layoutParams);
        bg.setBackgroundColor(Color.parseColor("#B3000000"));
        bg.setElevation(3);
        container.addView(bg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        container.removeView(bg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                //todo search hotel
                getActivity().onBackPressed();
                break;
            case R.id.btn_back:
            case R.id.background:
                getActivity().onBackPressed();
                break;
            case R.id.linearLayout:
PickCheckInDateFragment fragment = new PickCheckInDateFragment(filterViewModel.getCheckInDate().getValue(),this::pickDate);
requireActivity().getSupportFragmentManager().beginTransaction()
        .setCustomAnimations(R.anim.slide_in_up,R.anim.slide_in_down,R.anim.slide_out_down,R.anim.slide_out_up).add(R.id.fl_container,fragment).addToBackStack(null).commit();
                break;
            case R.id.ln_duration:
                Date date = new Date(filterViewModel.getCheckInDate().getValue());
                int dur = filterViewModel.getDuration().getValue();
                DurationFragment fragment1 = new DurationFragment(date,dur,this::pickDuration);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_up,R.anim.slide_in_down,R.anim.slide_out_down,R.anim.slide_out_up).add(R.id.fl_container,fragment1).addToBackStack(null).commit();
                break;
        }
    }

    @Override
    public void pickDuration(int night) {
        filterViewModel.setDuration(night);
    }

    @Override
    public void pickDate(Long date) {
        filterViewModel.setCheckInDate(date);
    }
}
