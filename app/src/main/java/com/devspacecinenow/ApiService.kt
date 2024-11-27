package com.devspacecinenow

import retrofit2.http.GET
import retrofit2.Call

interface ApiService {

    @GET("now_playing?language=en-US&page=1")
    fun getNowPlayingMovies() : Call<MovieResponse>

    @GET("upcoming?language=en-US&page=1")
    fun getUpComingMovies(): Call<MovieResponse>

    @GET("popular?language=en-US&page=1")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("top_rated?language=en-US&page=1")
    fun getTopRatedMovies(): Call<MovieResponse>
}