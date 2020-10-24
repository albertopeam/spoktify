package com.github.albertopeam.spoktify.ui.artist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.albertopeam.spoktify.databinding.ArtistFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistFragment: Fragment() {

    private lateinit var binding: ArtistFragmentBinding
    private val viewModel: ArtistViewModel by viewModels()
    private val args: ArtistFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.id = args.id
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ArtistFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}