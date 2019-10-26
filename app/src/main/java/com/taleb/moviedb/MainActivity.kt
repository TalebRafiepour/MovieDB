package com.taleb.moviedb

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moveRecyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)


        disposable.add(RxTextView
            .textChanges(searchEditText)
            .filter { it.length > 2 }
            .debounce(2,TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                RetrofitProvider
                    .provideRetrofit()
                    .getMovies(it.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(this, "result count : ${it.totalResults}", Toast.LENGTH_LONG).show()
                        moveRecyclerView.adapter = MoviesAdapter(it.results)
                    },{
                        Log.e("MYTAG","onError: ${it.message}")
                    })
            }, {
                Log.e("","onError : ${it.message}")
            }))

        /*disposable.add(RetrofitProvider
            .provideRetrofit()
            .getMovies("ta")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Toast.makeText(this, "result count : ${it.totalResults}", Toast.LENGTH_LONG).show()
                moveRecyclerView.adapter = MoviesAdapter(it.results)
            },{
                Log.e("MYTAG","onError: ${it.message}")
            }))*/
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
