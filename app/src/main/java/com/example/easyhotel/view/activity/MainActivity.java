package com.example.easyhotel.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.easyhotel.R;
import com.example.easyhotel.data.model.SearchModel;
import com.example.easyhotel.databinding.ActivityMainBinding;
import com.example.easyhotel.view.Event;
import com.example.easyhotel.view.adapter.MainBackgroundAdapter;
import com.example.easyhotel.view.fragment.PickCheckInDateFragment;
import com.example.easyhotel.view.fragment.SearchFragment;
import com.example.easyhotel.viewmodel.MainViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements Event {
    private ActivityMainBinding mBinding;
    private MainBackgroundAdapter mMainBackgroundAdapter;
    private int curren = Integer.MAX_VALUE / 2 + 1;
private FragmentManager fragmentManager;
private MainViewModel viewModel;
    BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_24px);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mMainBackgroundAdapter = new MainBackgroundAdapter();
        mBinding.setLifecycleOwner(this);
        mBinding.setViewmodel(viewModel);
        mBinding.background.setAdapter(mMainBackgroundAdapter);
        mBinding.setEvent(this);
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
            curren = mBinding.background.getCurrentItem();
            ++curren;
            mBinding.background.setCurrentItem(curren,true);
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
handler.post(update);
            }
        },4000,2000);
        fragmentManager = getSupportFragmentManager();

        bottomSheetBehavior = BottomSheetBehavior.from(mBinding.durationSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setPeekHeight(0);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mBinding.drawLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onLocalSearchClick() {
        mBinding.drawLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        SearchFragment searchFragment = new SearchFragment();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up,R.anim.slide_in_down,R.anim.slide_out_down,R.anim.slide_out_up).replace(R.id.main_container,searchFragment).addToBackStack(null).commit();
    }

    @Override
    public void onPickCheckIn() {
        mBinding.drawLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        PickCheckInDateFragment fragment = new PickCheckInDateFragment();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up,R.anim.slide_in_down,R.anim.slide_out_down,R.anim.slide_out_up).replace(R.id.main_container,fragment).addToBackStack(null).commit();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onPickDuration() {
        mBinding.drawLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void onSearchClick() {

    }

    @Override
    public void itemPlaceClick(SearchModel model) {
        viewModel.set_keyword(model.getTitle());
        onBackPressed();
    }

    @Override
    public void itemHotelClick() {

    }

    @Override
    public void itemGPSClick() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mBinding.drawLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

    }


}
