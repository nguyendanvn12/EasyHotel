package com.example.easyhotel.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyhotel.data.model.hoteldetails.HotelDetails;
import com.example.easyhotel.data.repository.DataRepo;
import com.example.easyhotel.data.repository.HotelRePo;

public class DetailsHotelViewModel extends ViewModel {
    private MutableLiveData<Boolean> _isTienNghiShow = new MutableLiveData<Boolean>(false);
    private MutableLiveData<Boolean> _isMoTaShow = new MutableLiveData<Boolean>(false);
    private MutableLiveData<Boolean> _isChinhSachShow = new MutableLiveData<Boolean>(false);
    private MutableLiveData<Long> checkIndate = new MutableLiveData<>();
    private MutableLiveData<Integer> duration = new MutableLiveData<>();



    private HotelRePo dataRepo = new HotelRePo();
    private MutableLiveData<HotelDetails> hotelDetails = new MutableLiveData<>();

    public LiveData<HotelDetails> getHotelDetails(int hotelId) {
        hotelDetails = dataRepo.getHotelDetails(hotelId);
        return hotelDetails;
    }

    public LiveData<Boolean> isTienNghiShow = _isTienNghiShow;
    public LiveData<Boolean> isMoTaShow = _isMoTaShow;
    public LiveData<Boolean> isChinhSachShow = _isChinhSachShow;


    public void tienNghiVisibilityChange(){
        this._isTienNghiShow.setValue( _isTienNghiShow.getValue()==true?false:true);
    }
    public void moTaVisibilityChange(){
        this._isMoTaShow.setValue( _isMoTaShow.getValue()==true?false:true);
    }
    public void chinhSachVisibilityChange(){
        this._isChinhSachShow.setValue( _isChinhSachShow.getValue()==true?false:true);
    }


}
