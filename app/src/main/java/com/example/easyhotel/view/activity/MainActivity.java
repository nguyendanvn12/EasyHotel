package com.example.easyhotel.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.ActivityMainBinding;
import com.example.easyhotel.view.adapter.MainBackgroundAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private MainBackgroundAdapter mMainBackgroundAdapter;
    private int curren = Integer.MAX_VALUE / 2 + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_24px);
        mMainBackgroundAdapter = new MainBackgroundAdapter();
        mBinding.background.setAdapter(mMainBackgroundAdapter);
        mBinding.background.setCurrentItem(Integer.MAX_VALUE / 2);

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {

                mBinding.background.setCurrentItem(curren, true);
                if (curren == Integer.MAX_VALUE) {
                    curren = 0;
                } else {
                    ++curren;
                }
            }
        };


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 2500);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mBinding.drawLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
