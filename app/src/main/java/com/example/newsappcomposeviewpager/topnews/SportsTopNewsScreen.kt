package com.example.newsappcomposeviewpager.topnews

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.newsappcomposeviewpager.news.NewsViewModel
import com.google.gson.Gson

@Composable
fun SportsTopNewsScreen(navController: NavController,
                  newsViewModel: NewsViewModel= hiltViewModel()
) {
    val getTopHeadlines by newsViewModel.getTopHeadlines.collectAsState()
    val apiKey="7cbc92e532304266bf56e47a07b45066"
    var country by remember {
        mutableStateOf("")
    }

    newsViewModel.getTopHeadlines(apiKey,country,"sports")
    Box(modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier.padding(8.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                value = country,
                onValueChange = {
                    country=it
                },
                placeholder = { Text("Country") }
            )
            Button(onClick = {newsViewModel.getTopHeadlines(apiKey,country,"sports")}) {
                Text("Got It")
            }
            LazyColumn (modifier = Modifier.weight(1f)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
               items(getTopHeadlines){ itemNews->
                   SportsNewsCard(
                       label = itemNews.title,
                       image = itemNews.urlToImage,
                       onClick = {
                           var encodedJson=Gson().toJson(itemNews)
                           var uriEncoded=Uri.encode(encodedJson)
                           navController.navigate("Detail/$uriEncoded")
                       }
                   )
               }
            }
        }
    }
}

@Composable
fun SportsNewsCard(label:String?,image:String?,onClick:()->Unit){
    Card (modifier = Modifier
        .height(150.dp)
        .width(200.dp)
        .clickable {
            onClick.invoke()
        },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp)
    ) {
        Column (modifier = Modifier
            .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = image ),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(contentAlignment = Alignment.Center){
                if (label != null) {
                    Text(label, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
            }
        }
    }
}
