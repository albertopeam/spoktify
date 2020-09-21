package com.github.albertopeam.spoktify.app.auth

import android.content.Context
import android.content.Intent
import com.github.albertopeam.spoktify.ui.auth.AuthenticationActivity
import com.github.albertopeam.usecases.auth.UnauthorizedChallenge

class UnauthorizedChallengeImplementation(private val context: Context): UnauthorizedChallenge {
    override fun challenge() {
        val intent = Intent(context, AuthenticationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}