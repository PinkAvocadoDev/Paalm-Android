package io.github.pinkavocadodev.paalm.dataclass

data class Day(
    val eval: String,
    val day: String,
    val uvIndex: Int,
    val uvEval: String,
    val rain: Double,
    val tempAvg: Int,
    val tempNow: Double,
    val clouds: Int,
    val windSpeed: Double,
    val weatherClr: Int,
    val isToday: Boolean
)
