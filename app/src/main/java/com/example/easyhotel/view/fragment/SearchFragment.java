package com.example.easyhotel.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyhotel.R;
import com.example.easyhotel.data.model.SearchModel;
import com.example.easyhotel.databinding.FragmentSearchBinding;
import com.example.easyhotel.view.CloseCallBack;
import com.example.easyhotel.view.activity.MainActivity;
import com.example.easyhotel.view.adapter.SearchAdapter;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private ArrayList<SearchModel> mSearchModels;
    private SearchAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false);
        adapter = new SearchAdapter();
        adapter.setEvent((MainActivity)getActivity());
        binding.rvSearchResult.setAdapter(adapter);
        binding.rvSearchResult.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setEvent(closeCallBack);
        initData();
        return binding.getRoot();
    }
private final CloseCallBack closeCallBack = new CloseCallBack() {
    @Override
    public void close() {
        getActivity().onBackPressed();
    }
};
    private void initData() {
        mSearchModels = new ArrayList<>();
        List<SearchModel>  history = new ArrayList<>();
        //todo load history search

         ArrayList<SearchModel>  famous = new ArrayList<>();
        famous.add(new SearchModel(1,1,"Hà Nội"));
        famous.add(new SearchModel(1,2,"Hồ Chí Minh"));
        mSearchModels.add(new SearchModel(0));
       if(history.size()>0){
           mSearchModels.add(new SearchModel(3,"Tìm kiếm gần đây"));
           mSearchModels.addAll(history);
       }
        mSearchModels.add(new SearchModel(3,"Địa điểm nổi bật"));
        mSearchModels.addAll(famous);
        adapter.setDatas(mSearchModels);
    }
}
