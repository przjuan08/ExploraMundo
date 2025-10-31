package sv.edu.udb.paisesweather.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstancia {

    private val retrofitPaises by lazy {
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitClima by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val servicioPaises: PaisApiService by lazy {
        retrofitPaises.create(PaisApiService::class.java)
    }

    val servicioClima: ClimaApiService by lazy {
        retrofitClima.create(ClimaApiService::class.java)
    }
}