package com.omertex.test.app.presentation.ui.fragment.merge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.omertex.test.app.R
import com.omertex.test.app.databinding.MergeFragmentBinding
import com.omertex.test.app.presentation.ui.NavigationViewModel
import com.omertex.test.app.presentation.ui.model.ViewModelState.*
import com.roger.catloadinglibrary.CatLoadingView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MergeFragment : Fragment() {

    private lateinit var binding: MergeFragmentBinding
    private var loadingView: CatLoadingView? = null

    private val navigationViewModel: NavigationViewModel by sharedViewModel()
    private val mergeViewModel: MergeViewModel by viewModel()

    private val mergeAdapter: MergeFragmentAdapter by lazy {
        MergeFragmentAdapter {
            navigationViewModel.openDetails(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.merge_fragment, container, false)
        binding.rvMerge.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mergeAdapter
        }

        mergeViewModel.mergeModel.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    Log.e(TAG, "Success, list size: ${it.data.size}")
                    hideProgress()
                    mergeAdapter.setData(it.data)
                    binding.rvMerge.visibility = View.VISIBLE
                }
                is Loading -> {
                    Log.e(TAG, "Loading")
                    showProgress()
                    binding.rvMerge.visibility = View.INVISIBLE
                }
                is Failed -> {
                    Log.e(TAG, "Failed")
                    hideProgress()
                    binding.rvMerge.visibility = View.INVISIBLE
                    Toast.makeText(context, "Failed load data", Toast.LENGTH_LONG).show()
                }
            }
        })


        return binding.root
    }


    private fun hideProgress() {
        loadingView?.dismiss()
        loadingView = null
    }

    private fun showProgress() {
        val target = activity ?: return
        val progress = loadingView ?: CatLoadingView().apply {
            setCanceledOnTouchOutside(false)
            show(target.supportFragmentManager, "")
        }
        loadingView = progress
    }

    companion object {
        private const val TAG = "MergeFragment"
    }
}
