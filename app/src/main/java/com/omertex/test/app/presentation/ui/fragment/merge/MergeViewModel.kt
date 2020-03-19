package com.omertex.test.app.presentation.ui.fragment.merge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omertex.test.app.core.datatype.SingleResult
import com.omertex.test.app.data.model.MergeModel
import com.omertex.test.app.domain.GetMergeListUseCase
import com.omertex.test.app.presentation.ui.model.ViewModelState
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class MergeViewModel(private val getMergeListUseCase: GetMergeListUseCase) : ViewModel() {


    private val mutableMergeModel = MutableLiveData<ViewModelState<List<MergeModel>>>()
    val mergeModel: LiveData<ViewModelState<List<MergeModel>>>
        get() = mutableMergeModel

    init {
        handleLoadMergeModel()
    }

    private fun handleLoadMergeModel() {
        viewModelScope.launch{
            mutableMergeModel.value = ViewModelState.loading()
            when(val result = getMergeListUseCase.execute().single()){
                is SingleResult.Success -> mutableMergeModel.postValue(ViewModelState.success(result.data))
                is SingleResult.Error -> mutableMergeModel.value = ViewModelState.failed()
                is SingleResult.Canceled -> mutableMergeModel.value = ViewModelState.failed()
            }
        }
    }
}
