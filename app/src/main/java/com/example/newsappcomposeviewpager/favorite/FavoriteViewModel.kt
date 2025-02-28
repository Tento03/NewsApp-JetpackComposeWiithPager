package com.example.newsappcomposeviewpager.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcomposeviewpager.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private var repository: FavoriteRepository):ViewModel() {
    private val _getFavorite = MutableStateFlow<List<Favorite>>(emptyList())
    var getFavorite:MutableStateFlow<List<Favorite>> = _getFavorite

    private val _getFavoriteId = MutableStateFlow<Favorite?>(null)
    var getFavoriteId:MutableStateFlow<Favorite?> = _getFavoriteId

    fun addFavorite(article: Article){
        var favorite=Favorite(
            author = article.author,
            content = article.content,
            description = article.description,
            publishedAt = article.publishedAt,
            title = article.title,
            url = article.url,
            urlToImage = article.urlToImage
        )
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addFavorite(favorite)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun getFavorite(){
        try {
            viewModelScope.launch {
                repository.getFavorite().collect{
                    _getFavorite.value=it
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun getFavoriteId(title:String){
        try {
            viewModelScope.launch {
                repository.getFavoriteId(title).collect{
                    _getFavoriteId.value=it
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun deleteFavorite(title: String){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteFavorite(title)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
}