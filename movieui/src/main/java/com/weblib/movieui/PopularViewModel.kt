package com.weblib.movieui

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weblib.apibusiness.PopularProvider
import com.weblib.metadata.Movie
import com.weblib.metadata.RequestResult
import com.weblib.movieui.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class PopularViewModel(private val popularProvider : PopularProvider) : ViewModel() {

    var isLoading: Boolean = false
    val movieList = MutableLiveData<List<Movie>>()
    val showError = SingleLiveEvent<String>()

    fun getAllPopulars() {
        isLoading = true
        viewModelScope.launch {
            val result =  popularProvider.getAllPopulars()
            isLoading = false
            when (result) {
                is RequestResult.Success -> {
                    movieList.value = result.successData.results
                    showError.value = null
                }
                is RequestResult.Error -> {
                    showError.value = result.message
                }
            }
        }
    }
}