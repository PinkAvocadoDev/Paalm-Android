package io.github.pinkavocadodev.paalm

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pinkavocadodev.paalm.dataclass.Beach
import io.github.pinkavocadodev.paalm.dataclass.Day
import io.github.pinkavocadodev.paalm.dataclass.Region
import io.github.pinkavocadodev.paalm.enums.Season
import io.github.pinkavocadodev.paalm.utility.RetrofitModule
import io.github.pinkavocadodev.paalm.utility.genDays
import java.time.LocalDate

@Composable
fun HomePage(modifier: Modifier = Modifier){
    val season = remember {
        mutableStateOf(getSeason())
    }

    val dropDwnStatus1 = remember {
        mutableStateOf(false)
    }
    val dropDwnStatus2 = remember {
        mutableStateOf(false)
    }

    val showDialog = remember {
        mutableStateOf(false)
    }

    val location = remember {
        mutableStateOf(Beach.LIDO_DI_OSTIA)
    }
    val selRegion = remember {
        mutableStateOf(Region.LAZIO)
    }

    var listOfDays by remember { mutableStateOf<List<Day>>(emptyList()) }

    val dialogDay = remember {
        mutableStateOf(Day("","",0,"",0.0,12,0.0,0,0.0,0, true))
    }

    LaunchedEffect(location.value) {
        try {
            val response = RetrofitModule.apiService.getWeather(
                     lat = location.value.lat,
                     lon = location.value.lon
                        )
            listOfDays = genDays(response)
        }catch (e: Exception){
            //elevation.value = e.message.toString()
        }
    }

    Column(
        modifier = modifier.fillMaxSize().background(Color.White)
    ) {
        Row(
            Modifier.fillMaxWidth().background(colorResource(season.value.color)).padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                Modifier.clickable(onClick = {dropDwnStatus1.value=true}).weight(1f)
            ) {
                Text(
                    text = selRegion.value.label,
                    Modifier.weight(5f),
                    color = Color.Black,

                    )

                Icon(Icons.Rounded.KeyboardArrowDown, "Open Locations", Modifier.weight(1f), tint = Color.Black)
            }

            DropdownMenu(
                expanded = dropDwnStatus1.value,
                onDismissRequest = {dropDwnStatus1.value = false},
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth().height(300.dp)
            ) {
                Region.entries.forEachIndexed { index, region ->
                    DropdownMenuItem(
                        onClick = {
                            selRegion.value = region
                            dropDwnStatus1.value = false
                            selRegion.value = region
                        },
                        text = {Text(region.label)}
                    )
                }
            }
            Spacer(Modifier.size(10.dp))
            Row(
                Modifier.clickable(onClick = {dropDwnStatus2.value=true}).weight(1f)
            ) {
                Text(
                    text = location.value.beachName,
                    Modifier.weight(5f),
                    color = Color.Black
                )

                Icon(
                    Icons.Rounded.KeyboardArrowDown,
                    "Open Locations",
                    Modifier.weight(1f),
                    tint = Color.Black
                )
            }
            DropdownMenu(
                expanded = dropDwnStatus2.value,
                onDismissRequest = {dropDwnStatus2.value = false},
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth().height(300.dp)
            ) {
                Beach.entries.forEachIndexed { index, beach ->
                    if(beach.region==selRegion.value) {
                        DropdownMenuItem(
                            onClick = {
                                location.value = beach
                                dropDwnStatus2.value = false
                                location.value = beach
                            },
                            text = { Text(beach.beachName) }
                        )
                    }
                }
            }

        }
        Row(
            Modifier.background(colorResource(season.value.color)).fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Siamo in:", color = Color.Black)
            Text(season.value.label, color = Color.Black)
        }
        Spacer(Modifier.size(5.dp))
        LazyColumn() {
            items(
                items = listOfDays,
                itemContent = { day ->
                    Card(
                        modifier = Modifier.fillMaxWidth().height(70.dp).padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Row(
                            modifier = Modifier.background(colorResource(day.weatherClr)).fillMaxWidth().padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(day.eval, color = Color.Black)
                            Row() {
                                Text(day.day, fontSize = 12.sp, color = Color.Black)
                                Spacer(Modifier.size(10.dp))
                                Button(
                                    onClick = {
                                        showDialog.value = true
                                        dialogDay.value = day
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                                ) {
                                    Icon(Icons.AutoMirrored.Rounded.ArrowForward, "Open Modal", tint = Color.Black)
                                }
                            }
                        }
                    }

            })
        }
        if(showDialog.value){
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                icon = { Icon(Icons.Rounded.Search, "Details") },
                title = {
                    Text(dialogDay.value.uvEval)
                },
                confirmButton = {
                    Button(onClick = { showDialog.value = false }) {
                        Text("Ok")
                    }
                },
                text = {
                    Column() {
                        if(dialogDay.value.isToday){
                            Text("Velocità vento: ${dialogDay.value.windSpeed}")
                            Text("Temperatura adesso: ${dialogDay.value.tempNow}°C")
                        }
                        Text("Indice UV: ${dialogDay.value.uvIndex}W/m²")
                        Text("Precipitazione: ${dialogDay.value.rain}mm")
                        Text("Media Temperatura: ${dialogDay.value.tempAvg}°C")
                        Text("Media Nuvolosità: ${dialogDay.value.clouds}%")
                    }
                }
            )
        }
    }
}

fun getSeason(): Season {
    val today = LocalDate.now()
    if(today.isBefore(LocalDate.parse("${today.year}-03-20")) && today.isAfter(LocalDate.parse("${today.year-1}-12-21"))){//Inverno
        return Season.INVERNO
    }else if(today.isBefore(LocalDate.parse("${today.year}-06-21")) && today.isAfter(LocalDate.parse("${today.year-1}-03-19"))){//Primavera
        return Season.PRIMAVERA
    }else if(today.isBefore(LocalDate.parse("${today.year}-09-23")) && today.isAfter(LocalDate.parse("${today.year-1}-06-20"))){//Estate
        return Season.ESTATE
    }else if (today.isBefore(LocalDate.parse("${today.year}-12-21")) && today.isAfter(LocalDate.parse("${today.year-1}-09-22"))){//Autunno
        return Season.AUTUNNO
    }
    return Season.ESTATE
}