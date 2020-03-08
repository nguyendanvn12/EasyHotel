package com.example.easyhotel.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.FragmentBookingBinding;
import com.example.easyhotel.viewmodel.BookingViewModel;
import com.example.easyhotel.viewmodel.DetailsHotelViewModel;
import com.example.easyhotel.viewmodel.ListHotelFilterViewModel;
import com.example.easyhotel.viewmodel.RoomViewModel;

public class BookingFragment extends Fragment {
    private FragmentBookingBinding binding;
    private DetailsHotelViewModel hotelViewModel;
    private ListHotelFilterViewModel filterViewModel;
    private BookingViewModel bookingViewModel;
    private RoomViewModel roomViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_booking, container, false);
        roomViewModel = new ViewModelProvider(requireActivity()).get(RoomViewModel.class);
        filterViewModel = new ViewModelProvider(requireActivity()).get(ListHotelFilterViewModel.class);
        hotelViewModel = new ViewModelProvider(requireActivity()).get(DetailsHotelViewModel.class);
        bookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setHotel(hotelViewModel);
        binding.setRoom(roomViewModel);
        binding.setFilter(filterViewModel);
        binding.setBooking(bookingViewModel);
        binding.rgSelectBed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                roomViewModel.selectBed(checkedId);
            }
        });
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        binding.rgSelectBed.check(roomViewModel.bed.getValue());
    }
}
