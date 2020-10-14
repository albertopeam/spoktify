package com.github.albertopeam.spoktify.ui.items

import com.github.albertopeam.spoktify.databinding.ItemPlaylistBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class ItemsAdapter: ListDelegationAdapter<List<Any>>() {
    init {
        val delegate = adapterDelegateViewBinding<PlaylistItemViewModel, Any, ItemPlaylistBinding>(
            { layoutInflater, root ->  ItemPlaylistBinding.inflate(layoutInflater, root, false) }) {
            bind {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
        delegatesManager.addDelegate(delegate)
    }
}