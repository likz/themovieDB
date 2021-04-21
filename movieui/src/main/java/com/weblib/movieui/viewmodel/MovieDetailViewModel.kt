package com.weblib.movieui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weblib.metadata.Movie
import com.weblib.metadata.database.MovieDao
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movieDao: MovieDao,
) : ViewModel() {

    var isLoading: Boolean = false
    val movie = MutableLiveData<Movie>()

    fun getMovieDetail(id: Int) {
        isLoading = true
        viewModelScope.launch {
            val movieDB = movieDao.getMovie(id)
            isLoading = false
            movie.value = movieDB
        }
    }

    fun updateMovie(movie: Movie) {
        isLoading = true
        viewModelScope.launch {
            movieDao.updateMovie(movie)
            isLoading = false
        }
    }
}