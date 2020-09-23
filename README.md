# spoktify
Spoktify is a example of usage of clean architecture using the Spotify API

## Installation

To use the app is mandatory to create an app in the Spotify Dashboard and inject in the system environment client_id` & `redirect_uri`, follow the next steps to do it.

1. You need to access Spotify [dashboard](https://developer.spotify.com/dashboard/)
2. Once access is granted, create a new app entry, more info on [Spotify](https://developer.spotify.com/documentation/general/guides/app-settings/). If you want to understand the authorization process(Implicit Grant Flow) you can check more information on [Spotify](https://developer.spotify.com/documentation/general/guides/authorization-guide/#implicit-grant-flow)
3. Once created the app we need two properties to make the app work:
   1. Client id
   2. Redirect Uri
4. Export the properties to the system environment
Edit .bash_profile(OSX) and add
```
    export SPOKTIFY_CLIENT_ID="your-client-id"
    export SPOKTIFY_REDIRECT_URI="your-redirect-uri"
```
