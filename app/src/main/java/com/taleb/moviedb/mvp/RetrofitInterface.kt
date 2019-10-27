package com.taleb.moviedb.mvp

import com.taleb.moviedb.MovieListClass
import com.taleb.moviedb.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("search/movie?api_key=d68cdc90928ad6e92d9d6d7491aa13eb")
    fun getMovies(@Query("query") movieName: String) : Observable<MovieListClass>


    @GET("find/{id}?api_key=d68cdc90928ad6e92d9d6d7491aa13eb")
    fun findMovie(@Query("id") movieId: Int) : Observable<Result>
}