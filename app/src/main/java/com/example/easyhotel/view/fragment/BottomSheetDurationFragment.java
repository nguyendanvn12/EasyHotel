package com.example.easyhotel.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.FragmentBottomSheetDurationBinding;
import com.example.easyhotel.view.adapter.DurationAdapter;
import com.example.easyhotel.viewmodel.MainViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.lang.reflect.Field;


public class BottomSheetDurationFragment extends BottomSheetDialogFragment {
    private FragmentBottomSheetDurationBinding binding;
    private MainViewModel viewModel;
    private customLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_bottom_sheet_duration, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        final DurationAdapter adapter = new DurationAdapter(viewModel.date.getValue());
        binding.durationList.setAdapter(adapter);
        layoutManager = new customLayoutManager(getContext());
        binding.durationList.setLayoutManager(layoutManager);
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.durationList);
        setCancelable(false);
        binding.durationList.smoothScrollToPosition(viewModel.duration.getValue()-1);
        binding.btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = snapHelper.findSnapView(layoutManager);
               int i =  binding.durationList.getChildAdapterPosition(view);
                viewModel.set_duration(i+1);
                dismiss();
            }
        });

        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return binding.getRoot();
    }


    class customLayoutManager extends LinearLayoutManager {

        public customLayoutManager(Context context) {
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

            int h1 = getChildAt(0)!=null? getChildAt(0).getMeasuredHeight():0;
            Log.d("ccc",h1+"");
            return Math.round((dpToPx(200)-h1) / 2);
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
