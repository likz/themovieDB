package com.weblib.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weblib.moviedb.utils.replaceFragment
import com.weblib.moviedb.view.PopularListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addPopularsFragment()
    }

    private fun addPopularsFragment() {
        replaceFragment(PopularListFragment(), R.id.fragment_container)
    }
}