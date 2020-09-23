package com.github.albertopeam.spoktify.ui.auth

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.albertopeam.usecases.auth.AuthenticationRepository

class AuthenticationViewModel @ViewModelInject constructor(@Assisted private val savedStateHandle: SavedStateHandle,
                                                           private val authenticationRepository: AuthenticationRepository): ViewModel() {
    val authURL: LiveData<String> = liveData {
        emit(authenticationRepository.authenticationUrl)
    }
}