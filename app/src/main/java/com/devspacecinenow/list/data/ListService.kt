package com.devspacecinenow.list.data

import com.devspacecinenow.common.model.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ListService {

    @GET("now_playing?language=en-US&page=1")
    suspend fun getNowPlayingMovies() : Response<MovieResponse>

    @GET("upcoming?language=en-US&page=1")
    fun getUpComingMovies(): Call<MovieResponse>

    @GET("popular?language=en-US&page=1")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("top_rated?language=en-US&page=1")
    fun getTopRatedMovies(): Call<MovieResponse>

}