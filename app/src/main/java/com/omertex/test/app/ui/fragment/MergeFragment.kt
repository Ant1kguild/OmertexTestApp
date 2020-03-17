package com.omertex.test.app.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.omertex.test.app.R

class MergeFragment : Fragment() {

    companion object {
        fun newInstance() = MergeFragment()
    }

    private lateinit var viewModel: MergeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.merge_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MergeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
