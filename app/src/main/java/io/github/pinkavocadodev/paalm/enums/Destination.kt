package io.github.pinkavocadodev.paalm.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val route: String,
    val label: String,
    val image: ImageVector,
    val desc: String
){
    HOME("home", "Home", Icons.Rounded.Home, "Home"),
    ABOUT("about", "About", Icons.Rounded.Favorite, "About")
}