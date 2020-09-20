package com.github.albertopeam.spoktify.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.albertopeam.data.BrowseFactory
import com.github.albertopeam.domain.Result
import com.github.albertopeam.usecases.exceptions.DataException
import kotlinx.coroutines.Dispatchers
import java.util.*

//TODO: inject params https://medium.com/@harmittaa/retrofit-2-6-0-with-koin-and-coroutines-4ff45a4792fc
class MainViewModel : ViewModel() {
    //TODO: coroutines from viewmodel, https://developer.android.com/topic/libraries/architecture/coroutines
    //TODO: https://proandroiddev.com/suspend-what-youre-doing-retrofit-has-now-coroutines-support-c65bd09ba067
    //TODO: koin https://medium.com/@harmittaa/retrofit-2-6-0-with-koin-and-coroutines-4ff45a4792fc

    var locale: Locale = Locale.getDefault()
    lateinit var unauthorized: ((Unit) -> Unit)
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val status: LiveData<String> = liveData(Dispatchers.IO) {
        loading.postValue(true)
        val repository = BrowseFactory.make(locale, unauthorized) //TODO: inject
        when (val result = repository.featured()) {
            is Result.Success -> { emit(result.data.toString()) }
            is Result.Error -> {
                when (result.exception) {
                    //TODO: código repetido a cojones en todos los VM
                    is DataException -> emit((result.exception as DataException).code.toString())
                    else -> emit("Something went wrong")
                }
            }
        }
        loading.postValue(false)
    }
}