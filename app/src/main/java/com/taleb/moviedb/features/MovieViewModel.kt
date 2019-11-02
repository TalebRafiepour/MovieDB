package com.taleb.moviedb.features

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taleb.moviedb.data.MovieModel
import com.taleb.moviedb.pojo.MovieListClass
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel : ViewModel(){

    private val model = MovieModel()
    private val disposable = CompositeDisposable()
    private val movieListLive = MutableLiveData<MovieListClass>()

    fun getMoviesContain(title: String) :LiveData<MovieListClass>{
        disposable.add(model.getMoviesContain(title)
            .subscribe({
                movieListLive.value = it
            },{
                Log.e("e","")
            })

        )
        return movieListLive
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}