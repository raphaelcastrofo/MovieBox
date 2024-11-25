package com.devspacecinenow

import retrofit2.http.GET
import retrofit2.Call

interface ApiService {

    @GET("now_playing?language=en-US&page=1")
    fun getNowPlayingMovies() : Call<MovieResponse>
}