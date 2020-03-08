package com.example.easyhotel.view.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.FragmentBookingBinding;
import com.example.easyhotel.databinding.FragmentSetRoomCountBinding;
import com.example.easyhotel.view.adapter.RoomCountAdapter;
import com.example.easyhotel.viewmodel.ListHotelFilterViewModel;

public class SetRoomCountFragment extends Fragment implements View.OnClickListener {
private FragmentSetRoomCountBinding binding;
private RoomCountAdapter adapter;
private ViewGroup container;
private View viewBg;
private ListHotelFilterViewModel filterViewModel;
private CustomLayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.container = container;
       binding = FragmentSetRoomCountBinding.inflate(inflater,container,false);
       filterViewModel = new ViewModelProvider(requireActivity()).get(ListHotelFilterViewModel.class);
       adapter = new RoomCountAdapter();
        SnapHelper snapHelper = new LinearSnapHelper();
        layoutManager = new CustomLayoutManager(getContext());
        binding.rvRoomCount.setLayoutManager(layoutManager);
        binding.rvRoomCount.setAdapter(adapter);
        snapHelper.attachToRecyclerView(binding.rvRoomCount);
        initBg();
        binding.rvRoomCount.smoothScrollToPosition(filterViewModel.getRoomCount().getValue()-1);
        binding.btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = snapHelper.findSnapView(layoutManager);
                int i = binding.rvRoomCount.getChildAdapterPosition(view);
                filterViewModel.setRoomCount(i+1);
                getActivity().onBackPressed();
            }
        });
        binding.close.setOnClickListener(this::onClick);
        binding.bg.setOnClickListener(this::onClick);

        return binding.getRoot();
    }
    private void initBg() {
        viewBg = new View(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewBg.setLayoutParams(layoutParams);
        viewBg.setBackgroundColor(Color.parseColor("#B3000000"));
        container.addView(viewBg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        container.removeView(viewBg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bg:
            case R.id.close:
                getActivity().onBackPressed();
        }
    }

    static class CustomLayoutManager extends LinearLayoutManager {

        CustomLayoutManager(Context context) {
            super(context);
        }


        public CustomLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public CustomLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        public int getPaddingTop() {

            int h1 = getChildAt(0) != null ? getChildAt(0).getMeasuredHeight() : 0;
            return Math.round((dpToPx(200) - h1) / 2);
        }

        @Override
        public int getPaddingBottom() {
            return getPaddingTop();
        }

        public int pxToDp(int px) {
            return (int) (px / Resources.getSystem().getDisplayMetrics().density);
        }

        public int dpToPx(int dp) {
            return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
        }

    }
}
