package com.example.newsappcomposeviewpager.uiux

import androidx.annotation.Nullable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.newsappcomposeviewpager.favorite.FavoriteViewModel
import com.example.newsappcomposeviewpager.model.Article
import com.example.newsappcomposeviewpager.news.NewsViewModel
import com.google.gson.Gson

@Composable
fun DetailScreen(newsJson: String?,
                 newsViewModel: NewsViewModel= hiltViewModel(),
                 favViewModel:FavoriteViewModel= hiltViewModel()
) {
    val details = Gson().fromJson(newsJson,Article::class.java)
    val getFavoriteId by favViewModel.getFavoriteId.collectAsState()
    favViewModel.getFavoriteId(details.title)

    LazyColumn (modifier = Modifier.padding(16.dp)
        .fillMaxSize()
    ) {
        item{
            if (details==null){
                Text(
                    text = "Loading or no data available...",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            else {
                Spacer(modifier = Modifier.height(100.dp))
                // Menampilkan gambar dan ikon favorite
                Row(modifier = Modifier.padding(bottom = 16.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(model = details.urlToImage),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .padding(end = 16.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite Icon",
                        tint = if (getFavoriteId==null) Color.Gray else Color.Red,
                        modifier = Modifier.size(48.dp)
                            .clickable {
                                if (getFavoriteId==null){
                                    favViewModel.addFavorite(details)
                                }
                                else{
                                    favViewModel.deleteFavorite(details.title)
                                }
                            }

                    )
                }

                Text("Description", fontWeight = FontWeight.Bold)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Text(details.description)
                        }
                    }
                }
            }
        }
    }
}