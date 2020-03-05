package com.example.easyhotel.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.FragmentBookingBinding;
import com.example.easyhotel.viewmodel.DetailsHotelViewModel;
import com.example.easyhotel.viewmodel.RoomViewModel;

public class BookingFragment extends Fragment {
    private FragmentBookingBinding binding;
    private DetailsHotelViewModel hotelViewModel;
    private RoomViewModel roomViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_booking, container, false);
        binding.setLifecycleOwner(getActivity());
        roomViewModel = new ViewModelProvider(getActivity()).get(RoomViewModel.class);
        hotelViewModel = new ViewModelProvider(getActivity()).get(DetailsHotelViewModel.class);
        Log.d("ccc", "onCreateView: "+hotelViewModel.hotelDetails.getValue().getHotelName());
        binding.setHotel(hotelViewModel);
        binding.setRoom(roomViewModel);
        return binding.getRoot();
    }
}
