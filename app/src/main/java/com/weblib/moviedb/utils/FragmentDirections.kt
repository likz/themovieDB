package com.weblib.moviedb.utils

import android.os.Bundle
import androidx.navigation.NavDirections
import com.weblib.moviedb.R

class FragmentDirections private constructor() {
    private data class ActionPopularsFragmentToMovieDetailsFragment(val movieId: Int) :
        NavDirections {
        override fun getActionId(): Int = R.id.action_popular_to_movie_detail_fragment
        override fun getArguments(): Bundle = Bundle().apply { putString("movieId", "$movieId") }
    }

    companion object {
        fun actionPopularsFragmentToMovieDetailsFragment(movieId: Int): NavDirections =
            ActionPopularsFragmentToMovieDetailsFragment(movieId)
    }
}
