package com.binaryBuddies.cinedore.ui.cine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CineViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}