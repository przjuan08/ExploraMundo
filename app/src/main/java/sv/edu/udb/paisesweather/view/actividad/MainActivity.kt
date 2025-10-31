package sv.edu.udb.paisesweather.view.actividad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sv.edu.udb.paisesweather.R

class MainActivity : AppCompatActivity() {

    private companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando MainActivity")

        try {
            setContentView(R.layout.actividad_main)
            Log.d(TAG, "onCreate: Layout cargado exitosamente")

            inicializarVista()
            configurarEventos()

        } catch (e: Exception) {
            Log.e(TAG, "Error en onCreate: ${e.message}", e)
            Toast.makeText(this, "Error al iniciar la aplicación", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun inicializarVista() {
        try {
            supportActionBar?.apply {
                title = getString(R.string.title_main)
                setDisplayShowHomeEnabled(true)
                setDisplayUseLogoEnabled(true)
            }
            Log.d(TAG, "inicializarVista: ActionBar configurada")
        } catch (e: Exception) {
            Log.e(TAG, "Error en inicializarVista: ${e.message}", e)
        }
    }

    private fun configurarEventos() {
        try {
            val btnExplorar = findViewById<Button>(R.id.btnExplorar)
            Log.d(TAG, "configurarEventos: Botón encontrado: ${btnExplorar != null}")

            btnExplorar.setOnClickListener {
                Log.d(TAG, "Botón Explorar clickeado")
                navegarARegiones()
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error en configurarEventos: ${e.message}", e)
            Toast.makeText(this, "Error al configurar botones", Toast.LENGTH_LONG).show()
        }
    }

    private fun navegarARegiones() {
        try {
            Log.d(TAG, "navegarARegiones: Intentando navegar a RegionesActivity")

            val intent = Intent(this, RegionesActivity::class.java)

            if (intent.resolveActivity(packageManager) != null) {
                Log.d(TAG, "navegarARegiones: Intent válido, iniciando actividad")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            } else {
                Log.e(TAG, "navegarARegiones: No se puede resolver la actividad RegionesActivity")
                Toast.makeText(this, "Error: No se puede abrir la pantalla de regiones", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error en navegarARegiones: ${e.message}", e)
            Toast.makeText(this, "Error al navegar: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: MainActivity está visible")
    }
}