package com.example.easyhotel.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.easyhotel.R;
import com.example.easyhotel.data.model.roominfo.Room;
import com.example.easyhotel.databinding.FragmentDetailsRoomBinding;
import com.example.easyhotel.view.RoomDetailsEvent;
import com.example.easyhotel.view.activity.DetailsHotelActivity;
import com.example.easyhotel.view.adapter.HotelImgViewpagerAdapter;
import com.example.easyhotel.viewmodel.DetailsHotelViewModel;
import com.example.easyhotel.viewmodel.RoomViewModel;


public class DetailsRoomFragment extends Fragment implements RoomDetailsEvent {
    private FragmentDetailsRoomBinding binding;
    private DetailsHotelViewModel viewModel;
    private RoomViewModel roomViewModel;
    private HotelImgViewpagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_room, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(DetailsHotelViewModel.class);
        roomViewModel = new ViewModelProvider(getActivity()).get(RoomViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setRoomViewModel(roomViewModel);
        adapter = new HotelImgViewpagerAdapter();
        roomViewModel.activeRoom.observe(getActivity(), new Observer<Room>() {
            @Override
            public void onChanged(Room room) {
                adapter.setImgs(room.getImgs());
            }
        });
        binding.vpSlider.setAdapter(adapter);
        binding.vpSlider.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float offset = 5 * position;
                page.setTranslationX(offset);

                if (position <= -1 || position >= 1) {
                    page.findViewById(R.id.imageViewID).setVisibility(View.VISIBLE);
                } else if (position == 0) {
                    page.findViewById(R.id.imageViewID).setVisibility(View.VISIBLE);
                    page.findViewById(R.id.imageViewID).setTranslationX(0);
                } else {
                    page.findViewById(R.id.imageViewID).setTranslationX(-position * page.getWidth() / 2);

                }
            }
        });
        binding.setEvent(this);
        binding.rgSelectBed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                roomViewModel.selectBed(checkedId);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void back() {
        getActivity().onBackPressed();
    }

    @Override
    public void call() {
        String tel = "tel:" + R.string.so_tong_dai;
        Uri uri = Uri.parse(tel);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void prevSlider() {
        int i = binding.vpSlider.getCurrentItem();
        try {
            binding.vpSlider.setCurrentItem(i - 1, true);
        } catch (Exception ex) {

        }
    }

    @Override
    public void nextSlider() {
        int i = binding.vpSlider.getCurrentItem();
        try {
            binding.vpSlider.setCurrentItem(i + 1, true);
        } catch (Exception ex) {

        }
    }

    private long mLastClickTime = 0;

    @Override
    public void booking() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        BookingFragment fragment = new BookingFragment();
        try {
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_out_right).replace(R.id.container, fragment).addToBackStack(null).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
