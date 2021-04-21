package com.weblib.moviedb.view.moviedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.weblib.metadata.Movie
import com.weblib.moviedb.databinding.FragmentMovieDetailsBinding
import com.weblib.movieui.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailsFragment : Fragment() {

    private val viewModel by viewModel<MovieDetailViewModel>()


    private val args: MovieDetailsFragmentArgs by navArgs()

    private var searchJob: Job? = null

    private var mMovie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMovieDetailsBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = this@MovieDetailsFragment
        viewModel = this@MovieDetailsFragment.viewModel

        detailHeaderStar.setOnRatingBarChangeListener { ratingBar, ratingValue, fromUser ->
            mMovie?.apply {
                vote_average = ratingValue
                this@MovieDetailsFragment.viewModel.updateMovie(this)
            }
        }

        this@MovieDetailsFragment.viewModel.movie.observe(viewLifecycleOwner) {
            mMovie = it
            Log.e("@@rating", it.toString())
        }
        search(args.movieId)
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //enable Back Button
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getMovieDetail(query.toInt())
        }
    }
}