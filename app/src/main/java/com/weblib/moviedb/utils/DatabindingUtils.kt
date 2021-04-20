package com.weblib.moviedb.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.weblib.metadata.Movie
import com.weblib.moviedb.R
import com.weblib.moviedb.view.populars.PopularListAdapter

fun getPosterPath(posterPath: String) = "https://image.tmdb.org/t/p/w342$posterPath"

@BindingAdapter("imageFromUrl")
fun bindingPopularPostUrl(view: ImageView, path: String?) {
    path?.let {
        Glide.with(view.context)
            .load(getPosterPath(it))
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(ContextCompat.getDrawable(view.context, R.drawable.page_not_found))
            .into(view)
    }
}

@BindingAdapter("adapterPopularList")
fun bindAdapterPopularList(view: RecyclerView, movies: List<Movie>?) {
    movies?.let {
        val adapter = view.adapter as? PopularListAdapter
        adapter?.addMovieList(it)
    }
}