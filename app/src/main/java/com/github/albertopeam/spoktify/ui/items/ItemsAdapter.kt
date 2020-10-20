package com.github.albertopeam.spoktify.ui.items

import com.github.albertopeam.spoktify.databinding.ItemArtistBinding
import com.github.albertopeam.spoktify.databinding.ItemCategoryBinding
import com.github.albertopeam.spoktify.databinding.ItemPlaylistBinding
import com.github.albertopeam.spoktify.ui.items.model.ArtistItemViewModel
import com.github.albertopeam.spoktify.ui.items.model.CategoryItemViewModel
import com.github.albertopeam.spoktify.ui.items.model.PlaylistItemViewModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class ItemsAdapter: ListDelegationAdapter<List<Any>>() {
    init {
        val playlistDelegate = adapterDelegateViewBinding<PlaylistItemViewModel, Any, ItemPlaylistBinding>(
            { layoutInflater, root ->  ItemPlaylistBinding.inflate(layoutInflater, root, false) }) {
            bind {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
        val artistDelegate = adapterDelegateViewBinding<ArtistItemViewModel, Any, ItemArtistBinding>(
            { layoutInflater, root ->  ItemArtistBinding.inflate(layoutInflater, root, false) }) {
            bind {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
        val categoriesDelegate = adapterDelegateViewBinding<CategoryItemViewModel, Any, ItemCategoryBinding>(
            { layoutInflater, root ->  ItemCategoryBinding.inflate(layoutInflater, root, false) }) {
            bind {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
        delegatesManager.addDelegate(playlistDelegate)
        delegatesManager.addDelegate(artistDelegate)
        delegatesManager.addDelegate(categoriesDelegate)
    }
}