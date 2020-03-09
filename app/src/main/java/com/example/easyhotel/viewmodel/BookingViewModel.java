package com.example.easyhotel.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyhotel.BR;
import com.example.easyhotel.data.model.hoteldetails.HotelDetails;
import com.example.easyhotel.data.model.roominfo.Room;

public class BookingViewModel extends ViewModel  {
     public MutableLiveData<Boolean> isCheckIn = new MutableLiveData<>(true);
     public MutableLiveData<Boolean> cmt = new MutableLiveData<>(false);
     public MutableLiveData<String> bookingFirstName = new MutableLiveData<>();
     public MutableLiveData<String> bookingLastName = new MutableLiveData<>();
     public MutableLiveData<String> bookingEmail = new MutableLiveData<>();
     public MutableLiveData<String> bookingPhoneNumber = new MutableLiveData<>();
     public MutableLiveData<String> checkInFirstName = new MutableLiveData<>();
     public MutableLiveData<String> checkInLastName = new MutableLiveData<>();
     public MutableLiveData<String> checkInPhoneNumber = new MutableLiveData<>();
     public MutableLiveData<String> bookingNote = new MutableLiveData<>();
     private MutableLiveData<Integer> roomCount = new MutableLiveData<>();
     private MutableLiveData<Long> checkInDate = new MutableLiveData<>();
     private MutableLiveData<Integer> duration = new MutableLiveData<>();
     public MutableLiveData<Integer> totalCost = new MutableLiveData<>();
     private MutableLiveData<HotelDetails> hotel = new MutableLiveData<>();
     private MutableLiveData<Room> room = new MutableLiveData<>();
     private MutableLiveData<Integer> rates = new MutableLiveData<>();
     private MutableLiveData<Integer> bed  = new MutableLiveData<>();

     public void setBed(int bed) {
          this.bed.setValue(bed);
     }

     public void initBookingInfo(DetailsHotelViewModel hotelViewModel, RoomViewModel roomViewModel, ListHotelFilterViewModel filterViewModel) {
          this.hotel = (MutableLiveData<HotelDetails>) hotelViewModel.hotelDetails;
          this.room = (MutableLiveData<Room>) roomViewModel.activeRoom;
          this.rates = (MutableLiveData<Integer>) roomViewModel.rate;
          this.bed = (MutableLiveData<Integer>) roomViewModel.bed;
          this.roomCount = (MutableLiveData<Integer>) filterViewModel.getRoomCount();
          this.duration = (MutableLiveData<Integer>) filterViewModel.getDuration();
          this.checkInDate = (MutableLiveData<Long>) filterViewModel.getCheckInDate();
          long startDiscount = this.room.getValue().getRates().get(this.rates.getValue()).getStartDiscount();
          long endDiscount = this.room.getValue().getRates().get(this.rates.getValue()).getEndDiscount();
          if (System.currentTimeMillis() / 1000 >= startDiscount && System.currentTimeMillis() / 1000 <= endDiscount) {
               int dis =this.room.getValue().getRates().get(this.rates.getValue()).getDiscount();
               int orig = this.room.getValue().getRates().get(this.rates.getValue()).getOriginPrice();
               int sale = orig - orig * dis / 100;
               totalCost.setValue(sale*roomCount.getValue()*duration.getValue());

          } else {
               int orig = this.room.getValue().getRates().get(this.rates.getValue()).getOriginPrice();
               totalCost.setValue(orig*roomCount.getValue()*duration.getValue());
          }
     }
}
