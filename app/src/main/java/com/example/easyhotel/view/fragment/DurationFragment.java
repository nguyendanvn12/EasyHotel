package com.example.easyhotel.view.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.FragmentDurationBinding;
import com.example.easyhotel.view.DurationCallback;
import com.example.easyhotel.view.adapter.DurationAdapter;

import java.util.Date;


public class DurationFragment extends Fragment implements View.OnClickListener {
    private customLayoutManager layoutManager;
    private FragmentDurationBinding binding;
    private Date checkInDate;
    private int duration;
    private DurationCallback callback;
    private ViewGroup container;
    private View viewBg;

    public DurationFragment(Date checkInDate, int duration, DurationCallback callback) {
        this.checkInDate = checkInDate;
        this.duration = duration;
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.container = container;
        initBg();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_duration, container, false);
        final DurationAdapter adapter = new DurationAdapter(checkInDate);
        binding.durationList.setAdapter(adapter);
        layoutManager = new customLayoutManager(getContext());
        binding.durationList.setLayoutManager(layoutManager);
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.durationList);

        binding.durationList.smoothScrollToPosition(duration-1);
        binding.btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = snapHelper.findSnapView(layoutManager);
                int i = binding.durationList.getChildAdapterPosition(view);
                callback.pickDuration(i+1);
                getActivity().onBackPressed();
            }
        });
        binding.close.setOnClickListener(this::onClick);
        binding.pickDurationBg.setOnClickListener(this::onClick);
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
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.close:
           case  R.id.pick_duration_bg:
            getActivity().onBackPressed();
            break;
        }
    }

    static class customLayoutManager extends LinearLayoutManager {

        customLayoutManager(Context context) {
            super(context);
        }


        public customLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public customLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
