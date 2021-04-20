package com.weblib.moviedb.view.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weblib.moviedb.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMovieDetailsBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = this@MovieDetailsFragment
    }.root
}