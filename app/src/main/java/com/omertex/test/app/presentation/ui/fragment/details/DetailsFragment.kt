package com.omertex.test.app.presentation.ui.fragment.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.request.CachePolicy

import com.omertex.test.app.R
import com.omertex.test.app.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    private lateinit var binding : DetailsFragmentBinding

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)


        binding.ivImage.load(args.model.urls!!.thumb){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            memoryCachePolicy(CachePolicy.ENABLED)
        }

        binding.tvName.text = args.model.name
        binding.tvEmail.text = args.model.email
        binding.tvWebsite.text = args.model.website
        binding.tvPhone.text = args.model.phone
        binding.tvCompanyName.text = args.model.company.companyName
        binding.tvCity.text = args.model.address.city

        return binding.root
    }



    companion object{
        private const val TAG = "DetailsFragment"
    }
}
