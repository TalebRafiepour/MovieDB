package com.taleb.moviedb.retrofit

import com.taleb.moviedb.pojo.MovieListClass
import com.taleb.moviedb.pojo.Result
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("search/movie?api_key=d68cdc90928ad6e92d9d6d7491aa13eb")
    fun getMovies(@Query("query") movieName: String) : Single<MovieListClass>


    @GET("find/{id}?api_key=d68cdc90928ad6e92d9d6d7491aa13eb")
    fun findMovie(@Query("id") movieId: Int) : Observable<Result>
}