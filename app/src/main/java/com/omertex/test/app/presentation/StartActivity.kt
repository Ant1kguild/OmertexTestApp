package com.omertex.test.app.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.omertex.test.app.R
import com.omertex.test.app.databinding.ActivityStartBinding
import com.omertex.test.app.presentation.ui.NavigationViewModel
import com.omertex.test.app.presentation.ui.fragment.merge.MergeFragmentDirections
import com.omertex.test.app.presentation.ui.model.ScreenModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    private lateinit var navController: NavController

    private var back_pressed: Long = 0

    private val navigationViewModel: NavigationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        navController = findNavController(R.id.nav_host_fragment)

        navigationViewModel.screenModel.observe(this, Observer {
            when (it) {
                is ScreenModel.Details -> navController.navigate(
                    MergeFragmentDirections.actionMergeToDetails(it.model)
                )
            }
        })
    }

    override fun onBackPressed() {
        when(navController.currentDestination?.id){
            R.id.nav_merge -> {
                if (back_pressed + 2000 > System.currentTimeMillis()) {
                    super.onBackPressed()
                } else {
                    Toast.makeText(baseContext, "Press once again to exit!", Toast.LENGTH_SHORT).show()
                }
                back_pressed = System.currentTimeMillis();
            }
            R.id.nav_details ->{
                navigationViewModel.openMerge()
                super.onBackPressed()
            }
        }
    }

    companion object {
        private const val TAG = "StartActivity"
    }
}
