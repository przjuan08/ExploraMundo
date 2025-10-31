package sv.edu.udb.paisesweather.view.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sv.edu.udb.paisesweather.R
import sv.edu.udb.paisesweather.model.Pais
import sv.edu.udb.paisesweather.util.cargarImagen
import sv.edu.udb.paisesweather.util.formatearPoblacion
import android.util.Log
import sv.edu.udb.paisesweather.util.Traductor

class PaisAdaptador(
    private var paises: List<Pais> = emptyList(),
    private val onPaisClickListener: (Pais) -> Unit
) : RecyclerView.Adapter<PaisAdaptador.PaisViewHolder>() {

    fun actualizarPaises(nuevosPaises: List<Pais>) {
        paises = nuevosPaises
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pais, parent, false)
        return PaisViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaisViewHolder, position: Int) {
        val pais = paises[position]
        holder.bind(pais)

        holder.itemView.setOnClickListener {
            onPaisClickListener(pais)
        }
    }

    override fun getItemCount(): Int = paises.size

    class PaisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgBandera: ImageView = itemView.findViewById(R.id.imgBandera)
        private val txtNombrePais: TextView = itemView.findViewById(R.id.txtNombrePais)
        private val txtCapital: TextView = itemView.findViewById(R.id.txtCapital)
        private val txtPoblacion: TextView = itemView.findViewById(R.id.txtPoblacion)
        private val txtRegion: TextView = itemView.findViewById(R.id.txtRegion)

        fun bind(pais: Pais) {
            try {
                imgBandera.cargarImagen(pais.urlBandera)

                txtNombrePais.text = pais.nombreComun
                txtCapital.text = pais.capitalPrimaria
                txtPoblacion.text = pais.poblacion.formatearPoblacion()
                txtRegion.text = Traductor.traducirRegion(pais.region)

            } catch (e: Exception) {
                Log.e("PaisAdaptador", "Error en bind: ${e.message}", e)
            }
        }
    }
}