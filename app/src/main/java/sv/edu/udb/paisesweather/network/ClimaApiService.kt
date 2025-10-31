package sv.edu.udb.paisesweather.network

import retrofit2.http.GET
import retrofit2.http.Query
import sv.edu.udb.paisesweather.model.Clima

interface ClimaApiService {

    @GET("current.json")
    suspend fun obtenerClimaActual(
        @Query("key") key: String,
        @Query("q") q: String
    ): Clima
}