package com.github.albertopeam.usecases.auth

//TODO: move to internal
class AuthenticationRepositoryImplementation: AuthenticationRepository {
    override val authenticationUrl: String
        get() {
            val clientId = System.getenv("SPOKTIFY_CLIENT_ID")
            val redirectUri = System.getenv("SPOKTIFY_REDIRECT_URI")
            val responseType = "token"
            val scopes = "user-read-email" + "%20"+ "user-read-private"
            val state = "spoktify"
            return "https://accounts.spotify.com/authorize?client_id=$clientId&response_type=$responseType&redirect_uri=$redirectUri&state=$state&scope=$scopes"
        }
}