package sv.edu.udb.paisesweather.view.adaptador

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import sv.edu.udb.paisesweather.R
import sv.edu.udb.paisesweather.model.Region

class RegionAdaptador(
    private val regiones: List<Region>,
    private val onRegionClickListener: (Region) -> Unit
) : RecyclerView.Adapter<RegionAdaptador.RegionViewHolder>() {

    private companion object {
        const val TAG = "RegionAdaptador"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        Log.d(TAG, "onCreateViewHolder: Creando nuevo ViewHolder")
        try {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_region, parent, false)
            return RegionViewHolder(view)
        } catch (e: Exception) {
            Log.e(TAG, "Error en onCreateViewHolder: ${e.message}", e)
            throw e
        }
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        try {
            val region = regiones[position]
            Log.d(TAG, "onBindViewHolder: Enlazando región ${region.nombre} en posición $position")
            holder.bind(region)

            holder.itemView.setOnClickListener {
                Log.d(TAG, "onClick: Región clickeada: ${region.nombre}")
                onRegionClickListener(region)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error en onBindViewHolder: ${e.message}", e)
        }
    }

    override fun getItemCount(): Int = regiones.size

    class RegionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(region: Region) {
            try {
                val imgRegion = itemView.findViewById<ImageView>(R.id.imgRegion)
                val txtNombreRegion = itemView.findViewById<TextView>(R.id.txtNombreRegion)
                val txtCantidadPaises = itemView.findViewById<TextView>(R.id.txtCantidadPaises)

                val iconoRes = when (region.nombre) {
                    "África" -> R.drawable.ic_globo
                    "América" -> R.drawable.ic_globo
                    "Asia" -> R.drawable.ic_globo
                    "Europa" -> R.drawable.ic_globo
                    "Oceanía" -> R.drawable.ic_globo
                    else -> R.drawable.ic_globo
                }

                val colorRes = when (region.nombre) {
                    "África" -> R.color.africa_color
                    "América" -> R.color.america_color
                    "Asia" -> R.color.asia_color
                    "Europa" -> R.color.europa_color
                    "Oceanía" -> R.color.oceania_color
                    else -> R.color.primary_color
                }

                imgRegion.setImageResource(iconoRes)
                imgRegion.setColorFilter(ContextCompat.getColor(itemView.context, colorRes))
                txtNombreRegion.text = region.nombre
                txtCantidadPaises.text = "Descubrir países"

            } catch (e: Exception) {
                Log.e("RegionViewHolder", "Error en bind: ${e.message}", e)
            }
        }
    }
}