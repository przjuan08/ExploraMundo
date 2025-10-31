package sv.edu.udb.paisesweather.model

data class Region(
    val nombre: String,
    val codigo: String,
    val paises: List<Pais> = emptyList()
) {
    companion object {
        // Regiones predefinidas según la API
        val REGIONES = listOf(
            Region("África", "Africa"),
            Region("América", "Americas"),
            Region("Asia", "Asia"),
            Region("Europa", "Europe"),
            Region("Oceanía", "Oceania")
        )
    }
}