package sv.edu.udb.paisesweather.view.actividad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import sv.edu.udb.paisesweather.R

class FallbackActivity : AppCompatActivity() {

    private companion object {
        const val TAG = "FallbackActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "FallbackActivity activada - Hubo un error crítico")

        try {
            setContentView(R.layout.activity_fallback)

            val errorMessage = intent.getStringExtra("error_message") ?: "Error desconocido"

            findViewById<TextView>(R.id.txtErrorMessage).text = errorMessage

            findViewById<Button>(R.id.btnRetry).setOnClickListener {
                restartApp()
            }

            findViewById<Button>(R.id.btnClose).setOnClickListener {
                finishAffinity()
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error incluso en FallbackActivity: ${e.message}")
            finishAffinity()
        }
    }

    private fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finishAffinity()
    }
}