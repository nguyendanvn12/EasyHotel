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
import com.example.easyhotel.viewmodel.ListHotelFilterViewModel;


public class ChangeFilterFragment extends Fragment implements View.OnClickListener {
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
        filterViewModel = new ViewModelProvider(requireActivity()).get(ListHotelFilterViewModel.class);
        Log.d("ccc", "onCreateView: ");
        binding.linearLayout.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
        binding.lnDuration.setOnClickListener(this);
        binding.background.setOnClickListener(this);
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
            case R.id.btn_back:
            case R.id.background:
                getActivity().onBackPressed();
                break;
            case R.id.linearLayout:

                break;
            case R.id.ln_duration:

                break;
        }
    }
}
