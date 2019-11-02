package com.taleb.moviedb.data

import com.taleb.moviedb.pojo.MovieListClass
import com.taleb.moviedb.retrofit.RetrofitProvider
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieModel() {

    fun getMoviesContain(title: String): Single<MovieListClass> {
        return RetrofitProvider.provideRetrofit()
            .getMovies(title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}