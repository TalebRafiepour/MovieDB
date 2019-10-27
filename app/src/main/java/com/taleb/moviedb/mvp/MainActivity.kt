package com.taleb.moviedb.mvp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.taleb.moviedb.R
import com.taleb.moviedb.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MovieContract.View {

    private var disposable = CompositeDisposable()
    private lateinit var movieRecyclerAdapter: MoviesAdapter
    private lateinit var moviesList: List<Result>
    private lateinit var presenter: MovieContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        presenter = MoviePresenter(this)

        disposable.add(RxTextView
            .textChanges(searchEditText)
            .filter { it.length > 2 }
            .debounce(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenter.getMoviesContain(it.toString())
            }, {
                Log.e("MYTAG", "onError : ${it.message}")
            })
        )
    }

    override fun setupRecyclerView() {
        moviesList = emptyList()
        movieRecyclerAdapter = MoviesAdapter(moviesList)
        moveRecyclerView.adapter = movieRecyclerAdapter
        moveRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun setMoviesList(list: List<Result>) {
        moviesList = list
        movieRecyclerAdapter.notifyDataSetChanged()
    }

    override fun loading(show: Boolean) {
        if (show)
            movieProgressBar.visibility = View.VISIBLE
        else movieProgressBar.visibility = View.GONE
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        presenter.onDestroy()
    }
}
