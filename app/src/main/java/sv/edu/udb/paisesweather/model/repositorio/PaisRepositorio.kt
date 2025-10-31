package sv.edu.udb.paisesweather.model.repositorio

import sv.edu.udb.paisesweather.model.Pais
import sv.edu.udb.paisesweather.model.Region
import sv.edu.udb.paisesweather.network.RetrofitInstancia
import java.io.IOException

class PaisRepositorio {

    private val servicioPaises = RetrofitInstancia.servicioPaises

    suspend fun obtenerTodosPaises(): Resultado<List<Pais>> {
        return try {
            val paises = servicioPaises.obtenerTodosPaises()
            Resultado.Exito(paises)
        } catch (e: IOException) {
            Resultado.Error("Error de conexión: ${e.message}")
        } catch (e: Exception) {
            Resultado.Error("Error inesperado: ${e.message}")
        }
    }

    suspend fun obtenerPaisesPorRegion(region: String): Resultado<List<Pais>> {
        return try {
            val paises = servicioPaises.obtenerPaisesPorRegion(region)
            Resultado.Exito(paises)
        } catch (e: IOException) {
            Resultado.Error("Error de conexión: ${e.message}")
        } catch (e: Exception) {
            Resultado.Error("Error inesperado: ${e.message}")
        }
    }

    suspend fun obtenerRegionesConPaises(): List<Region> {
        val regionesConPaises = mutableListOf<Region>()

        for (region in Region.REGIONES) {
            when (val resultado = obtenerPaisesPorRegion(region.codigo)) {
                is Resultado.Exito -> {
                    regionesConPaises.add(region.copy(paises = resultado.datos))
                }
                is Resultado.Error -> {
                    // Si hay error, agregamos la región sin países
                    regionesConPaises.add(region)
                }
            }
        }
        return regionesConPaises
    }
}

sealed class Resultado<out T> {
    data class Exito<T>(val datos: T) : Resultado<T>()
    data class Error(val mensaje: String) : Resultado<Nothing>()
}