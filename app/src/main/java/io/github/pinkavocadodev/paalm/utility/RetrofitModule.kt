package io.github.pinkavocadodev.paalm.utility
import io.github.pinkavocadodev.paalm.dataclass.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitModule {
    private const val BASE_URL = "https://api.open-meteo.com/"
    val apiService : WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}

interface WeatherApiService{
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("daily") daily: String = "sunset",
        @Query("timezone") tzone: String = "Europe/Berlin",
        @Query("current_weather") currWeather: Boolean = true,
        @Query("hourly") hourly: String = "temperature_2m,precipitation,cloudcover,diffuse_radiation",

        ): Response
}