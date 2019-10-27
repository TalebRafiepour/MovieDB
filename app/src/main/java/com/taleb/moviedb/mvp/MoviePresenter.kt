package com.taleb.moviedb.mvp

import com.taleb.moviedb.Result

class MoviePresenter(private val view: MovieContract.View) : MovieContract.Presenter{

    private val model = MovieModel(this)

    override fun getMoviesContain(title: String){
        view.loading(true)
        model.getMoviesContain(title)
    }

    override fun onMovieListReceived(list: List<Result>) {
        view.loading(false)
        view.setMoviesList(list)
    }

    override fun onMovieError(message: String) {
        view.loading(false)
        view.showToast(message)
    }

    override fun onDestroy() {
        model.onDestroy()
    }
}