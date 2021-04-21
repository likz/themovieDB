package com.weblib.moviedb.view.populars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weblib.moviedb.databinding.FragmentPopularBinding
import com.weblib.moviedb.view.RecyclerViewPaginator
import com.weblib.movieui.viewmodel.PopularViewModel
import kotlinx.android.synthetic.main.fragment_popular.*
import org.koin.android.viewmodel.ext.android.viewModel

class PopularListFragment : Fragment() {

    private val viewModel by viewModel<PopularViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPopularBinding.inflate(inflater, container, false).apply {
        val popularListAdapter = PopularListAdapter()
        subscribeUi(popularListAdapter)
        viewModel = this@PopularListFragment.viewModel
        lifecycleOwner = this@PopularListFragment
        populardapter = popularListAdapter
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        loadMore(page = 1)
    }

    private fun initializeUI() {
        RecyclerViewPaginator(
            recyclerView = popular_list,
            isLoading = { viewModel.isLoading },
            loadMore = { loadMore(it) },
            onLast = { false }
        ).apply {
            threshold = 4
            currentPage = 1
        }
    }

    private fun loadMore(page: Int) {
        viewModel.getAllPopulars(page)
    }

    private fun subscribeUi(adapter: PopularListAdapter) {
        viewModel.movieList.observe(viewLifecycleOwner) { adapter.addMovieList(it) }
    }
}