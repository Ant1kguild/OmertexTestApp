package com.omertex.test.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.omertex.test.app.R
import com.omertex.test.app.core.datatype.SingleResult
import com.omertex.test.app.data.datasource.PlaceHolderDataSource
import com.omertex.test.app.data.datasource.UnsplashDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class StartActivity : AppCompatActivity() {

    private val placeHolderDataSource : PlaceHolderDataSource by inject()
    private val unsplashDataSource : UnsplashDataSource by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        CoroutineScope(Dispatchers.IO).launch{
            when(val result = placeHolderDataSource.users()) {
                is SingleResult.Success -> {
                    val list = result.data
                    Log.e(TAG, "List user size: ${list.size}")
                }
            }

            when(val result = unsplashDataSource.photos(10)) {
                is SingleResult.Success -> {
                    val list = result.data
                    Log.e(TAG, "List photo: ${list.size}")
                }
                is SingleResult.Error -> Log.e(TAG, "List photo error: ${result.exception}")
            }
        }
    }

    companion object{
        private const val TAG = "StartActivity"
    }
}
