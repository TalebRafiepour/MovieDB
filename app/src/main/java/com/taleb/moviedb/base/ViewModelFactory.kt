package com.taleb.moviedb.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taleb.moviedb.features.MovieViewModel

class ViewModelFactory : ViewModelProvider.Factory {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel() as T
            else -> throw IllegalArgumentException("ViewModel is not provided")
        }
    }
}