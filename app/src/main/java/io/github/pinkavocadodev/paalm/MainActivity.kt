package io.github.pinkavocadodev.paalm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Posso andare al mare?")}
            )
        },
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
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
        composable("home"){
            HomePage(modifier)
        }
        composable("about") {
            AboutPage()
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