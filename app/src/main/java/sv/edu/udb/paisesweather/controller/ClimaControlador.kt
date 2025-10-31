package sv.edu.udb.paisesweather.controller

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import sv.edu.udb.paisesweather.model.Clima
import sv.edu.udb.paisesweather.model.repositorio.ClimaRepositorio
import sv.edu.udb.paisesweather.model.repositorio.Resultado

class ClimaControlador {

    private val repositorio = ClimaRepositorio()

    private val _estadoClima = MutableStateFlow<EstadoClima>(EstadoClima.Inicial)
    val estadoClima: StateFlow<EstadoClima> = _estadoClima

    suspend fun cargarClimaCapital(capital: String) {
        _estadoClima.value = EstadoClima.Cargando

        when (val resultado = repositorio.obtenerClimaCapital(capital)) {
            is Resultado.Exito -> {
                _estadoClima.value = EstadoClima.Exito(resultado.datos)
            }
            is Resultado.Error -> {
                _estadoClima.value = EstadoClima.Error(resultado.mensaje)
            }
        }
    }

    fun reiniciarEstado() {
        _estadoClima.value = EstadoClima.Inicial
    }

    sealed class EstadoClima {
        object Inicial : EstadoClima()
        object Cargando : EstadoClima()
        data class Exito(val clima: Clima) : EstadoClima()
        data class Error(val mensaje: String) : EstadoClima()
    }
}