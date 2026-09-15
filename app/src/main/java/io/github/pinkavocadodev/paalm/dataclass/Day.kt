package io.github.pinkavocadodev.paalm.dataclass

data class Day(
    val eval : String,
    val day : String,
    val uvIndex : Int,
    val uvEval : String,
    val rain : Int,
    val tempAvg: Int,
    val tempNow : Float,
    val clouds : Int,
    val windSpeed : Int,
    val weatherClr : Int
)
