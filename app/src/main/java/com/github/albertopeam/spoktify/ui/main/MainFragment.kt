package com.github.albertopeam.spoktify.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.ConfigurationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.albertopeam.spoktify.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java) //TODO: fix
        viewModel.locale = ConfigurationCompat.getLocales(resources.configuration)[0] //TODO: inject
        viewModel.unauthorized = {
            Log.d("MainFragment", "Unauthorized") //TODO: open activity. new intent
        }
        binding.viewmodel = viewModel
    }
}