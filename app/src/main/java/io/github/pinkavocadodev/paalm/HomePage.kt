package io.github.pinkavocadodev.paalm

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
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pinkavocadodev.paalm.enums.Season
import java.time.LocalDate

@Composable
fun HomePage(modifier: Modifier = Modifier){
    val season = remember {
        mutableStateOf(getSeason())
    }

    val dropDwnStatus = remember {
        mutableStateOf(false)
    }
    val selectedLocation = remember {
        mutableStateOf("Test location")
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            Modifier.fillMaxWidth().background(colorResource(season.value.color)).clickable(onClick = {dropDwnStatus.value=true}).padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = selectedLocation.value,
                Modifier.weight(5f)
            )

            Icon(Icons.Rounded.KeyboardArrowDown, "Open Locations", Modifier.weight(1f))

            DropdownMenu(
                expanded = dropDwnStatus.value,
                onDismissRequest = {dropDwnStatus.value = false},
                shape = RoundedCornerShape(20.dp),
               //containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            ) {
                DropdownMenuItem(
                    text = {Text("Test location")},
                    onClick = {}
                )
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
        HorizontalDivider(Modifier.padding(vertical = 5.dp))
        LazyColumn() {
            items(
                items = listOf<String>("Sì più o meno.","2","3","4","5","6","7"),
                key = {day -> day}
            ){day ->
                Card(
                    modifier = Modifier.fillMaxWidth().height(65.dp).padding(5.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(day)
                        Row() {
                            Text("Martedì 15", fontSize = 14.sp)
                            Spacer(Modifier.size(10.dp))
                            Button(onClick = {}) {
                                Icon(Icons.AutoMirrored.Rounded.ArrowForward, "Open Modal")
                            }
                        }
                    }
                }
            }
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