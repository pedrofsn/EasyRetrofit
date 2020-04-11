package br.com.redcode.easyreftrofit.library

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Created by pedrofsn on 24/08/2018.
 */
class APIConnection(
    private val apiBaseURL: String,
    private val okHttppBuilder: OkHttpClient.Builder,
    private val defaultConverterFactory: Converter.Factory
) {

    val buiderRetrofit = Retrofit.Builder()

    fun createRetrofit(): Retrofit = buiderRetrofit
        .baseUrl(apiBaseURL)
        .addConverterFactory(defaultConverterFactory)
        .client(okHttppBuilder.build())
        .build()

}