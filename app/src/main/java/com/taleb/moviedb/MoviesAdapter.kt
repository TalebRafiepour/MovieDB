package com.taleb.moviedb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_model_layout.view.*

class MoviesAdapter(private val movieList: List<Result>) :RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_model_layout,parent,false))
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }


    inner class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun onBind(movie: Result){
            itemView.movieTextView.text = movie.title
        }
    }
}