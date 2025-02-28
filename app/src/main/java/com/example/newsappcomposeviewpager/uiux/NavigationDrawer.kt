package com.example.newsappcomposeviewpager.uiux

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsappcomposeviewpager.model.Screens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .background(Color.Green)
                ) {}
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = {
                        println("Before navigate to Home")
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navController.navigate(Screens.Home.screen){
                            popUpTo(0)
                        }
                        println("After navigate to Home")
                    },
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
                )
                NavigationDrawerItem(
                    label = { Text("Favorite") },
                    selected = false,
                    onClick = {
                        println("Before navigate to Favorite")
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navController.navigate(Screens.Favorite.screen){
                            popUpTo(0)
                        }
                        println("After navigate to Favorite")
                    },
                    icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = null) },
                )
                NavigationDrawerItem(
                    label = { Text("Everything") },
                    selected = false,
                    onClick = {
                        println("Before navigate to Search")
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navController.navigate(Screens.Search.screen){
                            popUpTo(0)
                        }
                        println("After navigate to Search")
                    },
                    icon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Menu") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                        }
                    },
                )
            }
        ) {
            NavHost(navController = navController, startDestination = "Home") {
                composable(route = Screens.Home.screen) {
                    TabbedViewPager(navController)
                }
                composable(route = Screens.Favorite.screen) {
                    FavoriteScreen(navController)
                }
                composable(route=Screens.Search.screen) {
                    EverythingScreen(navController)
                }
               composable(route="Detail/{newsJson}",
                   arguments = listOf(navArgument("newsJson",{
                       type= NavType.StringType
                   }))
               ){
                   val newsJson=it.arguments?.getString("newsJson")
                   DetailScreen(newsJson)
               }
            }
        }
    }
}

