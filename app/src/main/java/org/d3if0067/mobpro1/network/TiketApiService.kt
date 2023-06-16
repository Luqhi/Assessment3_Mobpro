package org.d3if0067.mobpro1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0067.mobpro1.data.Tiket
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "Luqmanhakim13/Assessment3_Mobpro/master/static-api/"

//https://raw.githubusercontent.com/Luqmanhakim13/Assessment3_Mobpro/master/static-api/static-api.json

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TiketApiService {
    @GET("static-api.json")
    suspend fun getTiket(): List<Tiket>
}

object TiketApi {
    val service: TiketApiService by lazy {
        retrofit.create(TiketApiService::class.java)
    }

    fun getTiketUrl(imageId: String): String {
        return "$BASE_URL$imageId.png"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }