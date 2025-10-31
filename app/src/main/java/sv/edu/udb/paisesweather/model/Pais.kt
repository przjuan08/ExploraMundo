package sv.edu.udb.paisesweather.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Modelo que representa un país con toda su información
 * Incluye todos los datos de la API RestCountries
 */
data class Pais(
    @SerializedName("name") val nombre: NombrePais = NombrePais(),
    @SerializedName("capital") val capital: List<String>? = emptyList(),
    @SerializedName("region") val region: String = "Desconocida",
    @SerializedName("subregion") val subregion: String? = null,
    @SerializedName("population") val poblacion: Long = 0,
    @SerializedName("flags") val banderas: Banderas = Banderas(),
    @SerializedName("currencies") val monedas: Map<String, Moneda>? = emptyMap(),
    @SerializedName("languages") val idiomas: Map<String, String>? = emptyMap(),
    @SerializedName("latlng") val coordenadas: List<Double>? = emptyList(),
    @SerializedName("cca2") val codigoAlpha2: String = "",
    @SerializedName("cca3") val codigoAlpha3: String = "",
    @SerializedName("ccn3") val codigoNumerico: String? = null,
    @SerializedName("cioc") val codigoCIOC: String? = null,
    @SerializedName("independent") val independiente: Boolean? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("unMember") val miembroONU: Boolean? = null,
    @SerializedName("area") val area: Double? = null,
    @SerializedName("borders") val fronteras: List<String>? = emptyList(),
    @SerializedName("timezones") val zonasHorarias: List<String>? = emptyList()
) : Serializable {
    // Propiedades computadas con valores por defecto
    val nombreComun: String get() = nombre.comun ?: "Nombre no disponible"
    val nombreOficial: String get() = nombre.oficial ?: "Nombre oficial no disponible"
    val urlBandera: String get() = banderas.png ?: ""
    val capitalPrimaria: String get() = capital?.firstOrNull() ?: "No disponible"

    // Región y subregión traducidas
    val regionEspanol: String
        get() = sv.edu.udb.paisesweather.util.Traductor.traducirRegion(region)

    val subregionEspanol: String
        get() = subregion?.let { sv.edu.udb.paisesweather.util.Traductor.traducirSubregion(it) } ?: "No disponible"

    // Todas las monedas
    val todasMonedas: String
        get() = monedas?.entries?.joinToString(", ") { (codigo, moneda) ->
            val nombreTraducido = sv.edu.udb.paisesweather.util.Traductor.traducirMoneda(moneda.nombre ?: "Moneda")
            val simbolo = moneda.simbolo ?: ""
            "$nombreTraducido${if (simbolo.isNotBlank()) " ($simbolo - $codigo)" else " ($codigo)"}"
        } ?: "No disponible"

    // Todos los idiomas
    val todosIdiomas: String
        get() = idiomas?.values?.joinToString(", ") { idioma ->
            sv.edu.udb.paisesweather.util.Traductor.traducirIdioma(idioma)
        } ?: "No disponible"

    // Códigos ISO completos
    val codigosISO: String
        get() = buildString {
            append(codigoAlpha2)
            if (!codigoAlpha3.isNullOrEmpty()) append(", $codigoAlpha3")
            if (!codigoNumerico.isNullOrEmpty()) append(", $codigoNumerico")
            if (!codigoCIOC.isNullOrEmpty()) append(", $codigoCIOC")
            if (isEmpty()) append("No disponible")
        }

    // Coordenadas formateadas
    val coordenadasFormateadas: String
        get() = coordenadas?.let {
            if (it.size >= 2) {
                val lat = "%.2f".format(it[0])
                val lng = "%.2f".format(it[1])
                "$lat, $lng"
            } else "No disponible"
        } ?: "No disponible"
}

data class NombrePais(
    @SerializedName("common") val comun: String? = null,
    @SerializedName("official") val oficial: String? = null
) : Serializable

data class Banderas(
    @SerializedName("png") val png: String? = null,
    @SerializedName("svg") val svg: String? = null,
    @SerializedName("alt") val alt: String? = null
) : Serializable

data class Moneda(
    @SerializedName("name") val nombre: String? = null,
    @SerializedName("symbol") val simbolo: String? = null,
    @SerializedName("code") val codigo: String? = null
) : Serializable