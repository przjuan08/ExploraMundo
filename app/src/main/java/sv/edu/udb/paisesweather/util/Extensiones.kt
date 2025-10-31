package sv.edu.udb.paisesweather.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.cargarImagen(url: String) {
    Glide.with(this.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .into(this)
}

fun Long.formatearPoblacion(): String {
    return when {
        this >= 1_000_000 -> "${this / 1_000_000}M"
        this >= 1_000 -> "${this / 1_000}K"
        else -> this.toString()
    }
}

fun String.capitalizar(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

fun String.formatearNombreRegion(): String {
    return when (this.lowercase()) {
        "americas" -> "América"
        "europe" -> "Europa"
        "asia" -> "Asia"
        "africa" -> "África"
        "oceania" -> "Oceanía"
        else -> this.capitalizar()
    }
}