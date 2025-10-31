package sv.edu.udb.paisesweather.model.repositorio

import sv.edu.udb.paisesweather.model.Clima
import sv.edu.udb.paisesweather.network.RetrofitInstancia
import sv.edu.udb.paisesweather.util.Constantes
import java.io.IOException

class ClimaRepositorio {

    private val servicioClima = RetrofitInstancia.servicioClima

    suspend fun obtenerClimaCapital(capital: String): Resultado<Clima> {
        return try {
            // Usamos la API Key desde las constantes
            val clima = servicioClima.obtenerClimaActual(
                key = Constantes.WEATHER_API_KEY,
                q = capital
            )
            Resultado.Exito(clima)
        } catch (e: IOException) {
            Resultado.Error("Error de conexión: ${e.message}")
        } catch (e: Exception) {
            Resultado.Error("Error al obtener clima: ${e.message}")
        }
    }
}