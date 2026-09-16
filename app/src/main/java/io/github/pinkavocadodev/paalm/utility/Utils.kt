package io.github.pinkavocadodev.paalm.utility

import androidx.compose.remote.creation.compose.state.round
import io.github.pinkavocadodev.paalm.R
import io.github.pinkavocadodev.paalm.dataclass.Day
import io.github.pinkavocadodev.paalm.dataclass.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt
import kotlin.math.round

fun genDays(response: Response): List<Day> {
    val listOfDays = mutableListOf<Day>()

    for (day in 1..7) {
        var sT = 0.0
        var sP = 0.0
        var sC = 0.0
        var sR = 0.0

        val startHour = 8 * day
        val maxHour = startHour + 12

        for (i in startHour..<maxHour) {
            sT += response.hourly.temp[i]
            sP += response.hourly.precipitation[i]
            sC += response.hourly.cloud[i]
            sR += response.hourly.diffRad[i]
        }

        var todayPoints = 5
        val avgT = (sT / 12).roundToInt()
        val avgR = (sR / 12).roundToInt()
        val avgC = (sC / 12).roundToInt()
        var verdict = ""

        if (avgT in 19..23) {
            todayPoints--
        } else if (avgT < 18) {
            todayPoints -= 3
        }

        if (sP < 0.4 && sP > 0.1) {
            todayPoints--
        } else if (sP > 0.3) {
            todayPoints -= 3
        }

        if (avgC in 70..80) {
            todayPoints--
        } else if (avgC > 80) {
            todayPoints -= 3
        }

        val uvindex = (avgR / 25.0).roundToInt()
        var uvPoints = 5
        var verdictUv = ""

        if (uvindex > 10) {
            uvPoints -= 100
            verdictUv = "Rimani all'ombra."
        } else if (uvindex in 8..10) {
            uvPoints -= 4
            verdictUv = "Portati una crema molto forte."
        } else if (uvindex in 4..7) {
            uvPoints -= 3
            verdictUv = "Ricordati la crema! :)"
        } else {
            uvPoints -= 2
            verdictUv = "Il sole non è molto forte ma ricordati la crema! :)"
        }

        val weatherClr: Int = when (todayPoints) {
            4, 5 -> {
                verdict = "Sì! Fa pure bel tempo :)"
                R.color.Great
            }
            3->{
                verdict = "Più o meno.";
                R.color.Good
            }
            1,2->{
                verdict = "No, oggi non è giornata.";
                R.color.Bad
            }
            else->{
                verdict = "Assolutamente no :(";
                R.color.Awful
            }
        }

        val isToday = (day == 1)
        val dateStr = response.daily.time[day - 1]
        val date = LocalDate.parse(dateStr)
        val formatter = DateTimeFormatter.ofPattern("EEEE dd", Locale.ITALIAN)
        val formattedDate = date.format(formatter).replaceFirstChar { it.uppercase() }

        val dayObj = Day(verdict,formattedDate,avgR,verdictUv,round(sP * 10) / 10,avgT,response.currWeather.temp,avgC,response.currWeather.windspeed,weatherClr,isToday)
        listOfDays.add(dayObj)
    }
    return listOfDays
}