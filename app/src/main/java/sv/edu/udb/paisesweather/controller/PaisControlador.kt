package sv.edu.udb.paisesweather.controller

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import sv.edu.udb.paisesweather.model.Pais
import sv.edu.udb.paisesweather.model.Region
import sv.edu.udb.paisesweather.model.repositorio.PaisRepositorio
import sv.edu.udb.paisesweather.model.repositorio.Resultado

class PaisControlador {

    private val repositorio = PaisRepositorio()

    private val _estadoPaises = MutableStateFlow<EstadoPaises>(EstadoPaises.Inicial)
    val estadoPaises: StateFlow<EstadoPaises> = _estadoPaises

    private val _estadoRegiones = MutableStateFlow<EstadoRegiones>(EstadoRegiones.Inicial)
    val estadoRegiones: StateFlow<EstadoRegiones> = _estadoRegiones

    suspend fun cargarTodosPaises() {
        _estadoPaises.value = EstadoPaises.Cargando

        when (val resultado = repositorio.obtenerTodosPaises()) {
            is Resultado.Exito -> {
                _estadoPaises.value = EstadoPaises.Exito(resultado.datos)
            }
            is Resultado.Error -> {
                _estadoPaises.value = EstadoPaises.Error(resultado.mensaje)
            }
        }
    }

    suspend fun cargarPaisesPorRegion(region: String) {
        _estadoPaises.value = EstadoPaises.Cargando

        when (val resultado = repositorio.obtenerPaisesPorRegion(region)) {
            is Resultado.Exito -> {
                _estadoPaises.value = EstadoPaises.Exito(resultado.datos)
            }
            is Resultado.Error -> {
                _estadoPaises.value = EstadoPaises.Error(resultado.mensaje)
            }
        }
    }

    suspend fun cargarRegiones() {
        _estadoRegiones.value = EstadoRegiones.Cargando

        val regiones = repositorio.obtenerRegionesConPaises()
        _estadoRegiones.value = EstadoRegiones.Exito(regiones)
    }

    fun filtrarPaises(query: String, paises: List<Pais>): List<Pais> {
        return if (query.isBlank()) {
            paises
        } else {
            paises.filter { pais ->
                pais.nombreComun.contains(query, ignoreCase = true) ||
                        pais.nombreOficial.contains(query, ignoreCase = true) ||
                        pais.capitalPrimaria.contains(query, ignoreCase = true)
            }
        }
    }

    sealed class EstadoPaises {
        object Inicial : EstadoPaises()
        object Cargando : EstadoPaises()
        data class Exito(val paises: List<Pais>) : EstadoPaises()
        data class Error(val mensaje: String) : EstadoPaises()
    }

    sealed class EstadoRegiones {
        object Inicial : EstadoRegiones()
        object Cargando : EstadoRegiones()
        data class Exito(val regiones: List<Region>) : EstadoRegiones()
        data class Error(val mensaje: String) : EstadoRegiones()
    }
}