package com.taleb.moviedb.features

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.taleb.moviedb.R
import com.taleb.moviedb.base.ViewModelFactory
import com.taleb.moviedb.pojo.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var disposable = CompositeDisposable()
    private lateinit var movieRecyclerAdapter: MoviesAdapter
    private var moviesList: ArrayList<Result> = arrayListOf()
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        //viewModelFactory to pass the dataRepository to viewModel

        viewModel = ViewModelProvider(this,ViewModelFactory()).get(MovieViewModel::class.java)

        disposable.add(RxTextView
            .textChanges(searchEditText)
            .filter { it.length > 2 }
            .debounce(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loading(true)
                getMovie(it.toString())
            }, {
                Log.e("MYTAG", "onError : ${it.message}")
            })
        )
    }

    private fun getMovie(title :String){
        viewModel.getMoviesContain(title).observe(this, Observer {
            loading(false)
            setMoviesList(it.results)
        })
    }


    private fun setupRecyclerView() {
        movieRecyclerAdapter = MoviesAdapter(moviesList)
        movieRecyclerView.adapter = movieRecyclerAdapter
        movieRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun setMoviesList(list: List<Result>) {
        moviesList.clear()
        moviesList.addAll(list)
        movieRecyclerAdapter.notifyDataSetChanged()
    }

    private fun loading(show: Boolean) {
        if (show)
            movieProgressBar.visibility = View.VISIBLE
        else movieProgressBar.visibility = View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
