package com.example.easyhotel.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.easyhotel.R;
import com.example.easyhotel.data.model.SearchModel;
import com.example.easyhotel.data.model.searchresult.Hotel;
import com.example.easyhotel.data.model.searchresult.Region;
import com.example.easyhotel.data.model.searchresult.SearchResult;
import com.example.easyhotel.databinding.FragmentSearchBinding;
import com.example.easyhotel.view.CallBack;
import com.example.easyhotel.view.activity.MainActivity;
import com.example.easyhotel.view.adapter.SearchAdapter;
import com.example.easyhotel.viewmodel.SearchViewmodel;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private ArrayList<SearchModel> mSearchModels;
    private SearchAdapter adapter;
    private InputMethodManager imm;
    private SearchViewmodel searchViewmodel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        searchViewmodel = new ViewModelProvider(getActivity()).get(SearchViewmodel.class);
        adapter = new SearchAdapter();
        adapter.setEvent((MainActivity) getActivity());
        binding.rvSearchResult.setAdapter(adapter);
        binding.rvSearchResult.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setClose(callBack);
        binding.setEvent((MainActivity) getActivity());
        initData();
        binding.keywordText.requestFocus();
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        return binding.getRoot();
    }

    private final CallBack callBack = new CallBack() {
        @Override
        public void keywordChanged(CharSequence text) {
            if (text.length() >= 3) {
                searchViewmodel.getSearchResult(text.toString()).observe(getViewLifecycleOwner(), new Observer<SearchResult>() {
                    @Override
                    public void onChanged(SearchResult searchResult) {
                        mSearchModels = new ArrayList<>();
                        if (searchResult.getRegion().size() > 0) {
                            mSearchModels.add(new SearchModel(3, "Địa điểm"));
                            for (Region region : searchResult.getRegion()
                            ) {
                                mSearchModels.add(new SearchModel(1,region.getId(),region.getName()));
                            }
                        }
                        if (searchResult.getHotel().size() > 0) {
                            mSearchModels.add(new SearchModel(3, "Khách sạn"));
                            for (Hotel hotel : searchResult.getHotel()
                            ) {
                                mSearchModels.add(new SearchModel(2,hotel.getHotelId(),hotel.getHotelName(),hotel.getHotelAddr()));
                            }
                        }
                        adapter.setDatas(mSearchModels);

                    }

                });
            } else if (text.length() == 0) {
                initData();
            }
        }

        @Override
        public void close() {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            getActivity().onBackPressed();
        }
    };

    private void initData() {
        mSearchModels = new ArrayList<>();
        List<SearchModel> history = new ArrayList<>();
        //todo load history search

        ArrayList<SearchModel> famous = new ArrayList<>();
        famous.add(new SearchModel(1, 1, "Hà Nội"));
        famous.add(new SearchModel(1, 2, "Hồ Chí Minh"));
        mSearchModels.add(new SearchModel(0));
        if (history.size() > 0) {
            mSearchModels.add(new SearchModel(3, "Tìm kiếm gần đây"));
            mSearchModels.addAll(history);
        }
        mSearchModels.add(new SearchModel(3, "Địa điểm nổi bật"));
        mSearchModels.addAll(famous);
        adapter.setDatas(mSearchModels);
    }
}
