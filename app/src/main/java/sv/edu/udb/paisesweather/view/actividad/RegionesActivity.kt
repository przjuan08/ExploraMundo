package sv.edu.udb.paisesweather.view.actividad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sv.edu.udb.paisesweather.R
import sv.edu.udb.paisesweather.model.Region
import sv.edu.udb.paisesweather.util.Constantes
import sv.edu.udb.paisesweather.view.adaptador.RegionAdaptador

class RegionesActivity : AppCompatActivity() {

    private companion object {
        const val TAG = "RegionesActivity"
    }

    private lateinit var recyclerView: RecyclerView

    private val regiones = listOf(
        Region("África", "Africa", emptyList()),
        Region("América", "Americas", emptyList()),
        Region("Asia", "Asia", emptyList()),
        Region("Europa", "Europe", emptyList()),
        Region("Oceanía", "Oceania", emptyList())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando RegionesActivity")

        try {
            setContentView(R.layout.actividad_regiones)
            Log.d(TAG, "onCreate: Layout cargado exitosamente")

            inicializarVistas()
            configurarToolbar()
            configurarRecyclerView()

        } catch (e: Exception) {
            Log.e(TAG, "Error en onCreate: ${e.message}", e)
            Toast.makeText(this, "Error al cargar regiones", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun inicializarVistas() {
        try {
            recyclerView = findViewById(R.id.rvRegiones)
            Log.d(TAG, "inicializarVistas: RecyclerView encontrado: ${::recyclerView.isInitialized}")
        } catch (e: Exception) {
            Log.e(TAG, "Error en inicializarVistas: ${e.message}", e)
            throw e
        }
    }

    private fun configurarToolbar() {
        try {
            val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)

            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                title = "Regiones del Mundo"
            }

            toolbar.setNavigationOnClickListener {
                Log.d(TAG, "Botón retroceso clickeado")
                onBackPressedDispatcher.onBackPressed()
            }

            Log.d(TAG, "configurarToolbar: Toolbar configurada exitosamente")
        } catch (e: Exception) {
            Log.e(TAG, "Error en configurarToolbar: ${e.message}", e)
        }
    }

    private fun configurarRecyclerView() {
        try {
            val adaptador = RegionAdaptador(regiones) { region ->
                Log.d(TAG, "Región seleccionada: ${region.nombre}")
                navegarAListaPaises(region)
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@RegionesActivity)
                adapter = adaptador
                setHasFixedSize(true)
            }

            Log.d(TAG, "configurarRecyclerView: RecyclerView configurado con ${regiones.size} regiones")
        } catch (e: Exception) {
            Log.e(TAG, "Error en configurarRecyclerView: ${e.message}", e)
            Toast.makeText(this, "Error al configurar la lista", Toast.LENGTH_LONG).show()
        }
    }

    private fun navegarAListaPaises(region: Region) {
        try {
            Log.d(TAG, "navegarAListaPaises: Intentando navegar a ListaPaisesActivity con región: ${region.nombre}")

            val intent = Intent(this, ListaPaisesActivity::class.java).apply {
                putExtra(Constantes.EXTRA_REGION, region.codigo)
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                Log.d(TAG, "navegarAListaPaises: Navegación exitosa")
            } else {
                Log.e(TAG, "navegarAListaPaises: No se puede resolver ListaPaisesActivity")
                Toast.makeText(this, "Error: No se puede abrir la lista de países", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error en navegarAListaPaises: ${e.message}", e)
            Toast.makeText(this, "Error al navegar: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: RegionesActivity está visible")
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d(TAG, "onSupportNavigateUp: Navegación up")
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}