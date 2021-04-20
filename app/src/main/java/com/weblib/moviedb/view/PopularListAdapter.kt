package com.weblib.moviedb.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.RecyclerView
import com.weblib.metadata.Movie
import com.weblib.moviedb.R
import com.weblib.moviedb.databinding.PopularListBinding

class PopularListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), LifecycleObserver {
    private val popularList: MutableList<Movie> = mutableListOf()

    fun addMovieList(movies: List<Movie>) {
        popularList.addAll(movies)
        notifyItemRangeInserted(popularList.size - movies.size + 1, movies.size)
    }

    override fun getItemViewType(position: Int): Int =  R.layout.popular_list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PopularListViewHolder(
                PopularListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PopularListViewHolder).bindData(position)
    }

    inner class PopularListViewHolder(
            private val binding: PopularListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var popular: Movie

        fun bindData(position: Int) {
            popular = popularList[position]
            binding.apply {
                movie = popular
                setClickListener {
                    navigateToMovie(popular)
                }
                executePendingBindings()
            }
        }

        private fun navigateToMovie(
                movie: Movie,
        ) {
            Log.e("@@Movie onClick", movie.toString())
        }
    }

    override fun getItemCount(): Int {
        return popularList.size
    }
}