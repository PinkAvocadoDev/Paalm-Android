package io.github.pinkavocadodev.paalm

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.pinkavocadodev.paalm.enums.Destination
import io.github.pinkavocadodev.paalm.ui.theme.PaalmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaalmTheme {
                Root()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Root(){
    val navController = rememberNavController()
    val startDestination = Destination.HOME.route
    var selectedDestination by rememberSaveable { mutableStateOf(Destination.HOME.ordinal)}
    var season = remember {
        mutableStateOf(getSeason())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Posso andare al mare?", color = Color.Black)},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(season.value.color))
            )
        },
        bottomBar = {
            Column(
                Modifier.fillMaxWidth()
            ) {
                HorizontalDivider()
                NavigationBar(
                    windowInsets = NavigationBarDefaults.windowInsets,
                    containerColor = Color.White
                ) {
                    Destination.entries.forEachIndexed { index, destination ->
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color.Black, unselectedIconColor =Color.Black, selectedTextColor = Color.Black, unselectedTextColor =Color.Black, indicatorColor =Color.LightGray),
                            selected = selectedDestination == index,
                            onClick = {
                                selectedDestination = index
                                navController.navigate(destination.route){
                                    popUpTo(Destination.HOME.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true

                                }

                            },
                            icon = {Icon(destination.image, destination.desc)},
                            label = {Text(destination.label)}
                        )
                    }
                }
            }
        },
        content = { padding ->
            NavigationFramework(navController, startDestination, Modifier.padding(padding))
        }
    )
}

@Composable
fun NavigationFramework(navCont: NavHostController, startDest : String, modifier: Modifier = Modifier){
    NavHost(
        navController = navCont,
        startDestination = startDest
    ){
        composable(Destination.HOME.route) {
            HomePage(modifier)
        }
        composable(Destination.ABOUT.route) {
            AboutPage(modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PaalmTheme {
        //Root()
        HomePage()
    }
}