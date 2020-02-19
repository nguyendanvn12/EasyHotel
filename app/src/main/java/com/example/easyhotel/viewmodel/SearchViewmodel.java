package com.example.easyhotel.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyhotel.data.model.searchresult.SearchResult;
import com.example.easyhotel.data.repository.DataRepo;

public class SearchViewmodel extends ViewModel {
    private DataRepo dataRepo = new DataRepo();

    public LiveData<SearchResult> getSearchResult(String keword){
        return dataRepo.getSearchResult(keword);
    }

}
