package sv.edu.udb.paisesweather.model

import com.google.gson.annotations.SerializedName

data class Clima(
    @SerializedName("location") val ubicacion: Ubicacion,
    @SerializedName("current") val actual: ClimaActual
)

data class Ubicacion(
    @SerializedName("name") val nombre: String,
    @SerializedName("country") val pais: String
)

data class ClimaActual(
    @SerializedName("temp_c") val temperaturaC: Double,
    @SerializedName("temp_f") val temperaturaF: Double,
    @SerializedName("condition") val condicion: CondicionClima,
    @SerializedName("wind_kph") val velocidadVientoKph: Double,
    @SerializedName("wind_mph") val velocidadVientoMph: Double,
    @SerializedName("humidity") val humedad: Int,
    @SerializedName("feelslike_c") val sensacionTermicaC: Double
) {
    val temperaturaFormateada: String get() = "${temperaturaC}°C"
    val humedadFormateada: String get() = "$humedad%"
    val vientoFormateado: String get() = "${velocidadVientoKph} km/h"
}

data class CondicionClima(
    @SerializedName("text") val texto: String,
    @SerializedName("icon") val icono: String
)