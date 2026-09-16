package io.github.pinkavocadodev.paalm.dataclass
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("hourly")
    val hourly : HourlyData,
    @SerializedName("daily")
    val daily : DailyData,
    @SerializedName("current_weather")
    val currWeather: CurrWthrData
)

data class HourlyData(
    @SerializedName("temperature_2m")
    val temp: List<Double>,
    @SerializedName("precipitation")
    val precipitation: List<Double>,
    @SerializedName("cloudcover")
    val cloud: List<Double>,
    @SerializedName("diffuse_radiation")
    val diffRad: List<Double>
)

data class DailyData(
    @SerializedName("time")
    val time: List<String>
)

data class CurrWthrData(
    @SerializedName("temperature")
    val temp: Double,
    @SerializedName("windspeed")
    val windspeed:Double
)