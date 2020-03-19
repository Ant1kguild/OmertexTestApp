package com.omertex.test.app.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omertex.test.app.data.model.MergeModel
import com.omertex.test.app.presentation.ui.model.ScreenModel

class NavigationViewModel : ViewModel() {

    //LiveData --> screen
    private val mutableScreenModel: MutableLiveData<ScreenModel> = MutableLiveData()
    val screenModel: LiveData<ScreenModel> = mutableScreenModel

    init {
        mutableScreenModel.value = ScreenModel.Merge
    }


    fun openDetails(data: MergeModel){
        if (mutableScreenModel.value is ScreenModel.Merge){
            mutableScreenModel.value = ScreenModel.Details(data)
        }
    }

    fun openMerge(){
        mutableScreenModel.value = ScreenModel.Merge
    }
}