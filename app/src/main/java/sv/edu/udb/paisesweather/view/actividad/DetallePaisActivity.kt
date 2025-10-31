package sv.edu.udb.paisesweather.view.actividad

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.launch
import sv.edu.udb.paisesweather.R
import sv.edu.udb.paisesweather.controller.ClimaControlador
import sv.edu.udb.paisesweather.model.Pais
import sv.edu.udb.paisesweather.util.Constantes
import sv.edu.udb.paisesweather.util.formatearPoblacion
import android.widget.Toast
import sv.edu.udb.paisesweather.util.Traductor

class DetallePaisActivity : AppCompatActivity() {

    private lateinit var controladorClima: ClimaControlador
    private var pais: Pais? = null

    // Views principales
    private lateinit var imgBanderaGrande: ImageView
    private lateinit var txtNombrePais: TextView
    private lateinit var txtNombreOficial: TextView

    // Información del país
    private lateinit var txtCapital: TextView
    private lateinit var txtRegion: TextView
    private lateinit var txtSubregion: TextView
    private lateinit var txtPoblacion: TextView
    private lateinit var txtMoneda: TextView
    private lateinit var txtIdioma: TextView

    private lateinit var txtCodigosISO: TextView
    private lateinit var txtCoordenadas: TextView
    private lateinit var txtMonedas: TextView // Para todas las monedas
    private lateinit var txtIdiomas: TextView // Para todos los idiomas

    // Views del clima
    private lateinit var cardClima: MaterialCardView
    private lateinit var layoutCargandoClima: View
    private lateinit var layoutErrorClima: View
    private lateinit var layoutDatosClima: View
    private lateinit var imgIconoClima: ImageView
    private lateinit var txtTemperatura: TextView
    private lateinit var txtCondicionClima: TextView
    private lateinit var txtHumedad: TextView
    private lateinit var txtViento: TextView
    private lateinit var txtSensacionTermica: TextView
    private lateinit var txtErrorClima: TextView
    private lateinit var btnActualizarClima: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_detalle_pais)

        try {
            val paisObtenido = obtenerPaisDesdeIntent()

            if (paisObtenido != null) {
                pais = paisObtenido
                inicializarControladores()
                inicializarVistas()
                configurarToolbar()
                mostrarInformacionPais()

                if (pais!!.capitalPrimaria != "No disponible") {
                    cargarClimaCapital()
                }
            } else {
                Toast.makeText(this, "Error: No se pudo cargar la información del país", Toast.LENGTH_LONG).show()
                finish()
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Error al cargar detalles", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun obtenerPaisDesdeIntent(): Pais? {
        return try {
            val pais = intent.getSerializableExtra(Constantes.EXTRA_PAIS) as? Pais
            pais
        } catch (e: Exception) {
            null
        }
    }

    private fun inicializarControladores() {
        controladorClima = ClimaControlador()
    }

    private fun inicializarVistas() {
        try {
            // Views principales
            imgBanderaGrande = findViewById(R.id.imgBanderaGrande)
            txtNombrePais = findViewById(R.id.txtNombrePais)
            txtNombreOficial = findViewById(R.id.txtNombreOficial)

            // Información del país (EXISTENTES)
            txtCapital = findViewById(R.id.txtCapital)
            txtRegion = findViewById(R.id.txtRegion)
            txtSubregion = findViewById(R.id.txtSubregion)
            txtPoblacion = findViewById(R.id.txtPoblacion)

            // NUEVAS VISTAS
            txtCodigosISO = findViewById(R.id.txtCodigosISO)
            txtCoordenadas = findViewById(R.id.txtCoordenadas)
            txtMonedas = findViewById(R.id.txtMonedas)
            txtIdiomas = findViewById(R.id.txtIdiomas)

            // Views del clima
            cardClima = findViewById(R.id.cardClima)
            layoutCargandoClima = findViewById(R.id.layoutCargandoClima)
            layoutErrorClima = findViewById(R.id.layoutErrorClima)
            layoutDatosClima = findViewById(R.id.layoutDatosClima)
            imgIconoClima = findViewById(R.id.imgIconoClima)
            txtTemperatura = findViewById(R.id.txtTemperatura)
            txtCondicionClima = findViewById(R.id.txtCondicionClima)
            txtHumedad = findViewById(R.id.txtHumedad)
            txtViento = findViewById(R.id.txtViento)
            txtSensacionTermica = findViewById(R.id.txtSensacionTermica)
            txtErrorClima = findViewById(R.id.txtErrorClima)
            btnActualizarClima = findViewById(R.id.btnActualizarClima)

            // Configurar botón de actualizar clima
            btnActualizarClima.setOnClickListener {
                cargarClimaCapital()
            }

        } catch (e: Exception) {
            throw e
        }
    }

    private fun configurarToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = pais?.nombreComun ?: "Detalles del País"
        }
    }

    private fun mostrarInformacionPais() {
        val paisActual = pais ?: run {
            Toast.makeText(this, "Error: No hay datos del país", Toast.LENGTH_LONG).show()
            return
        }

        try {
            // Cargar bandera
            Glide.with(this)
                .load(paisActual.urlBandera)
                .placeholder(R.drawable.ic_globo)
                .error(R.drawable.ic_globo)
                .into(imgBanderaGrande)

            // Información básica
            txtNombrePais.text = paisActual.nombreComun
            txtNombreOficial.text = paisActual.nombreOficial

            // Información detallada - TODOS LOS DATOS
            txtCapital.text = paisActual.capitalPrimaria
            txtRegion.text = paisActual.regionEspanol
            txtSubregion.text = paisActual.subregionEspanol
            txtPoblacion.text = paisActual.poblacion.formatearPoblacion()

            // NUEVOS DATOS
            txtCodigosISO.text = paisActual.codigosISO
            txtCoordenadas.text = paisActual.coordenadasFormateadas
            txtMonedas.text = paisActual.todasMonedas
            txtIdiomas.text = paisActual.todosIdiomas

            // Mantener compatibilidad con vistas antiguas
            txtMoneda.text = paisActual.todasMonedas
            txtIdioma.text = paisActual.todosIdiomas

            // Configurar content descriptions para accesibilidad
            imgBanderaGrande.contentDescription = "Bandera de ${paisActual.nombreComun}"

        } catch (e: Exception) {
        }
    }

    private fun cargarClimaCapital() {
        val paisActual = pais ?: return

        lifecycleScope.launch {
            mostrarEstadoCargandoClima()

            controladorClima.cargarClimaCapital(paisActual.capitalPrimaria)

            controladorClima.estadoClima.collect { estado ->
                when (estado) {
                    is ClimaControlador.EstadoClima.Cargando -> {
                        mostrarEstadoCargandoClima()
                    }
                    is ClimaControlador.EstadoClima.Exito -> {
                        mostrarClima(estado.clima)
                    }
                    is ClimaControlador.EstadoClima.Error -> {
                        mostrarErrorClima(estado.mensaje)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun mostrarEstadoCargandoClima() {
        cardClima.isVisible = true
        layoutCargandoClima.isVisible = true
        layoutErrorClima.isVisible = false
        layoutDatosClima.isVisible = false
    }

    private fun mostrarClima(clima: sv.edu.udb.paisesweather.model.Clima) {
        // Cargar icono del clima
        Glide.with(this)
            .load("https:${clima.actual.condicion.icono}")
            .into(imgIconoClima)

        // Mostrar datos principales - TRADUCIDOS
        txtTemperatura.text = clima.actual.temperaturaFormateada
        txtCondicionClima.text = Traductor.traducirCondicionClima(clima.actual.condicion.texto)
        txtHumedad.text = clima.actual.humedadFormateada
        txtViento.text = clima.actual.vientoFormateado
        txtSensacionTermica.text = "${clima.actual.sensacionTermicaC}°C"

        // Mostrar vistas
        cardClima.isVisible = true
        layoutCargandoClima.isVisible = false
        layoutErrorClima.isVisible = false
        layoutDatosClima.isVisible = true

        // Configurar content descriptions
        imgIconoClima.contentDescription = Traductor.traducirCondicionClima(clima.actual.condicion.texto)
    }

    private fun mostrarErrorClima(mensaje: String) {
        txtErrorClima.text = mensaje

        cardClima.isVisible = true
        layoutCargandoClima.isVisible = false
        layoutErrorClima.isVisible = true
        layoutDatosClima.isVisible = false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::controladorClima.isInitialized) {
            controladorClima.reiniciarEstado()
        }
    }
}