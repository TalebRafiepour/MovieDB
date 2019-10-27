package com.taleb.moviedb.mvp

import com.taleb.moviedb.Result

interface MovieContract {

    interface View {
        fun setupRecyclerView()
        fun setMoviesList(list: List<Result>)
        fun loading(show: Boolean)
        fun showToast(message: String)
    }


    interface Presenter {
        fun getMoviesContain(title: String)
        fun onMovieListReceived(list: List<Result>)
        fun onMovieError(message: String)
        fun onDestroy()
    }

}