package sv.edu.udb.paisesweather.view.actividad

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.text.Editable
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.launch
import sv.edu.udb.paisesweather.R
import sv.edu.udb.paisesweather.controller.PaisControlador
import sv.edu.udb.paisesweather.model.Pais
import sv.edu.udb.paisesweather.util.Constantes
import sv.edu.udb.paisesweather.util.formatearNombreRegion
import sv.edu.udb.paisesweather.view.adaptador.PaisAdaptador

class ListaPaisesActivity : AppCompatActivity() {

    private lateinit var controlador: PaisControlador
    private lateinit var adaptador: PaisAdaptador

    // Views
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutCargando: View
    private lateinit var layoutError: View
    private lateinit var layoutSinResultados: View
    private lateinit var cardBusqueda: MaterialCardView
    private lateinit var etBuscar: EditText
    private lateinit var btnLimpiarBusqueda: ImageButton

    private var region: String = ""
    private var todosPaises: List<Pais> = emptyList()
    private var paisesFiltrados: List<Pais> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_lista_paises)

        // Obtener región del intent
        region = intent.getStringExtra(Constantes.EXTRA_REGION) ?: ""

        inicializarControlador()
        inicializarVistas()
        configurarToolbar()
        configurarRecyclerView()
        configurarBusqueda()
        cargarPaises()
    }

    private fun inicializarControlador() {
        controlador = PaisControlador()
    }

    private fun inicializarVistas() {
        recyclerView = findViewById(R.id.rvPaises)
        layoutCargando = findViewById(R.id.layoutCargando)
        layoutError = findViewById(R.id.layoutError)
        layoutSinResultados = findViewById(R.id.layoutSinResultados)
        cardBusqueda = findViewById(R.id.cardBusqueda)
        etBuscar = findViewById(R.id.etBuscar)
        btnLimpiarBusqueda = findViewById(R.id.btnLimpiarBusqueda)

        findViewById<View>(R.id.btnReintentar).setOnClickListener {
            cargarPaises()
        }
    }

    private fun configurarToolbar() {
        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.title_lista_paises, region.formatearNombreRegion())
        }

        toolbar.menu?.clear()

        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun configurarRecyclerView() {
        adaptador = PaisAdaptador(emptyList()) { pais ->
            Log.d("ListaPaisesActivity", "Navegando a detalle del país: ${pais.nombreComun}")
            navegarADetallePais(pais)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListaPaisesActivity)
            adapter = adaptador
            setHasFixedSize(true)
        }
    }

    private fun navegarADetallePais(pais: Pais) {
        try {
            Log.d("ListaPaisesActivity", "Navegando a detalle del país: ${pais.nombreComun}")

            val intent = Intent(this, DetallePaisActivity::class.java).apply {
                putExtra(Constantes.EXTRA_PAIS, pais as java.io.Serializable)
            }

            Log.d("ListaPaisesActivity", "Intent creado, iniciando actividad...")
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            Log.d("ListaPaisesActivity", "Navegación exitosa a DetallePaisActivity")

        } catch (e: Exception) {
            Log.e("ListaPaisesActivity", "Error al navegar a detalle: ${e.message}", e)
            Toast.makeText(this, "Error al abrir detalles: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun configurarBusqueda() {
        btnLimpiarBusqueda.setOnClickListener {
            etBuscar.setText("")
            filtrarPaises("")
        }

        etBuscar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                filtrarPaises(etBuscar.text.toString())
                true
            } else {
                false
            }
        }

        etBuscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filtrarPaises(s.toString())
            }
        })
    }

    private fun cargarPaises() {
        lifecycleScope.launch {
            mostrarEstadoCargando()

            controlador.cargarPaisesPorRegion(region)

            controlador.estadoPaises.collect { estado ->
                when (estado) {
                    is PaisControlador.EstadoPaises.Cargando -> {
                        mostrarEstadoCargando()
                    }
                    is PaisControlador.EstadoPaises.Exito -> {
                        todosPaises = estado.paises
                        paisesFiltrados = estado.paises
                        mostrarPaises(estado.paises)
                    }
                    is PaisControlador.EstadoPaises.Error -> {
                        mostrarError(estado.mensaje)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun filtrarPaises(query: String) {
        paisesFiltrados = controlador.filtrarPaises(query, todosPaises)
        adaptador.actualizarPaises(paisesFiltrados)

        layoutSinResultados.isVisible = paisesFiltrados.isEmpty() && query.isNotBlank()
        recyclerView.isVisible = paisesFiltrados.isNotEmpty()
    }

    private fun mostrarEstadoCargando() {
        layoutCargando.isVisible = true
        layoutError.isVisible = false
        recyclerView.isVisible = false
        layoutSinResultados.isVisible = false
        cardBusqueda.isVisible = false
    }

    private fun mostrarPaises(paises: List<Pais>) {
        adaptador.actualizarPaises(paises)

        layoutCargando.isVisible = false
        layoutError.isVisible = false
        recyclerView.isVisible = paises.isNotEmpty()
        layoutSinResultados.isVisible = paises.isEmpty()
        cardBusqueda.isVisible = paises.isNotEmpty()
    }


    private fun mostrarError(mensaje: String) {
        findViewById<android.widget.TextView>(R.id.txtMensajeError).text = mensaje

        layoutCargando.isVisible = false
        layoutError.isVisible = true
        recyclerView.isVisible = false
        layoutSinResultados.isVisible = false
        cardBusqueda.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}