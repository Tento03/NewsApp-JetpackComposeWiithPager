package com.example.newsappcomposeviewpager.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcomposeviewpager.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private var repository: NewsRepository):ViewModel() {
    private var _getTopHeadlines=MutableStateFlow<List<Article>>(emptyList())
    var getTopHeadlines:MutableStateFlow<List<Article>> = _getTopHeadlines

    private var _getEverything=MutableStateFlow<List<Article>>(emptyList())
    var getEverything:MutableStateFlow<List<Article>> =_getEverything

    fun getTopHeadlines(apiKey:String,country:String,category:String){
        try {
            viewModelScope.launch {
                var response=repository.getTopHeadlines(apiKey,country, category).articles
                _getTopHeadlines.value=response
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun getEverything(apiKey: String,q:String){
        try {
            viewModelScope.launch {
                var response=repository.getEverything(apiKey, q).articles
                _getEverything.value=response
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
}