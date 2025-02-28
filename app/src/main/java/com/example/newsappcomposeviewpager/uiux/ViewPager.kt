package com.example.newsappcomposeviewpager.uiux

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsappcomposeviewpager.topnews.BusinessTopNewsScreen
import com.example.newsappcomposeviewpager.topnews.EntertaimentTopNewsScreen
import com.example.newsappcomposeviewpager.topnews.GeneralTopNewsScreen
import com.example.newsappcomposeviewpager.topnews.HealthTopNewsScreen
import com.example.newsappcomposeviewpager.topnews.ScienceTopNewsScreen
import com.example.newsappcomposeviewpager.topnews.SportsTopNewsScreen
import com.example.newsappcomposeviewpager.topnews.TechnologyTopNewsScreen
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
fun TabbedViewPager(navController: NavController){
    val tabList= listOf("General", "Business", "Health", "Science", "Sports", "Technology", "Entertainment")
    var pagerState = rememberPagerState()
    var targetPage by remember {
        mutableStateOf(-1)
    }

    Column(modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        ScrollableTabRow(
            tabList = tabList,
            pagerState = pagerState,
            onTabSelected = { index->
                targetPage=index
            }
        )

        HorizontalPager(
            count = tabList.size,
            modifier = Modifier.weight(1f),
            state = pagerState,
        ) { page->
            when (page){
                0-> GeneralTopNewsScreen(navController)
                1-> BusinessTopNewsScreen(navController)
                2-> HealthTopNewsScreen(navController)
                3-> ScienceTopNewsScreen(navController)
                4-> SportsTopNewsScreen(navController)
                5-> TechnologyTopNewsScreen(navController)
                6-> EntertaimentTopNewsScreen(navController)
            }
        }
    }

    if (targetPage!=-1){
        LaunchedEffect(targetPage) {
            pagerState.animateScrollToPage(targetPage)
            targetPage=-1
        }
    }
}

@Composable
fun ScrollableTabRow(
    tabList:List<String>,
    pagerState:PagerState,
    onTabSelected: (Int) -> Unit
) {
    val scrollState= rememberScrollState()

    Row(modifier = Modifier
        .padding(8.dp)
        .horizontalScroll(scrollState)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabList.forEachIndexed { index, s ->
            val isSelected= pagerState.currentPage==index

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable {
                        onTabSelected(index)
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(s)

                if (isSelected) {
                    Box(modifier = Modifier
                        .height(2.dp)
                        .width(40.dp)
                        .background(Color.Blue))
                }
            }
        }
    }

}
