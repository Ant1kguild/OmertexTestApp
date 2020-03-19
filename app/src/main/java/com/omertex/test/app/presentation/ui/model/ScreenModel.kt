package com.omertex.test.app.presentation.ui.model

import com.omertex.test.app.data.model.MergeModel


sealed class ScreenModel {
    object Merge : ScreenModel()
    data class Details(val model: MergeModel) : ScreenModel()
}

