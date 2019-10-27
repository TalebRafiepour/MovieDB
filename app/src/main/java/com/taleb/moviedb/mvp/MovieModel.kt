package com.taleb.moviedb.mvp

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieModel(private val presenter: MovieContract.Presenter) {

    private val disposable = CompositeDisposable()

    fun getMoviesContain(title: String) {
        disposable.add(
            RetrofitProvider.provideRetrofit()
                .getMovies(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it1 ->
                    presenter.onMovieListReceived(it1.results)
                }, { it1 ->
                    Log.e("error",it1.message ?: "")
                    presenter.onMovieError(it1.message ?: "some error occurred in getting data from server.")
                })
        )
    }

    fun onDestroy() {
        disposable.clear()
    }

}