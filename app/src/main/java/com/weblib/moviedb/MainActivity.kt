package com.weblib.moviedb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.weblib.movieui.PopularViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val popularViewModel by viewModel<PopularViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popularViewModel.getAllPopulars()

        popularViewModel.movieList.observe(this, {
            Log.e("@@Populars", it.size.toString())
            if (it.isNotEmpty() && it != null) {
                Log.e("Populars", it.toString())
            }
        })
    }
}