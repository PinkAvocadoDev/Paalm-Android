package io.github.pinkavocadodev.paalm.enums

import io.github.pinkavocadodev.paalm.R

enum class Season(
    val label:String,
    val color:Int
) {
    ESTATE("Estate", R.color.Summer),
    INVERNO("Inverno", R.color.Winter),
    PRIMAVERA("Primavera", R.color.Spring),
    AUTUNNO("Autunno", R.color.Autumn)
}