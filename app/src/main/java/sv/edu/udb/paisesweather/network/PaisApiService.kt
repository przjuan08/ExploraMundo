package sv.edu.udb.paisesweather.network

import retrofit2.http.GET
import retrofit2.http.Path
import sv.edu.udb.paisesweather.model.Pais

interface PaisApiService {

    @GET("all")
    suspend fun obtenerTodosPaises(): List<Pais>

    @GET("region/{region}")
    suspend fun obtenerPaisesPorRegion(@Path("region") region: String): List<Pais>

    @GET("alpha/{codigo}")
    suspend fun obtenerPaisPorCodigo(@Path("codigo") codigo: String): List<Pais>
}