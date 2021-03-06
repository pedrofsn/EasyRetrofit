package br.com.redcode.easyreftrofit.samplekotlin.network.infra

import br.com.redcode.easyreftrofit.library.APIConnection
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by pedrofsn on 31/10/2018.
 */
abstract class AbstractAPIConnection<T>(
    baseURL: String,
    showLogs: Boolean,
    interceptor: Interceptor
) {

    abstract val classz: Class<T>
    private val moshi by lazy { MoshiConverterFactory.create() }

    val service: T by lazy {
        val okHttppBuilder = OkHttpClient().newBuilder()
        okHttppBuilder.connectTimeout(1, TimeUnit.MINUTES)
        okHttppBuilder.readTimeout(1, TimeUnit.MINUTES)
        okHttppBuilder.addInterceptor(interceptor)

        val logging = HttpLoggingInterceptor()
        logging.level =
            if (showLogs) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        okHttppBuilder.addInterceptor(logging)

        val apiConnection = APIConnection(
            apiBaseURL = baseURL,
            okHttppBuilder = okHttppBuilder,
            defaultConverterFactory = moshi
        )

        val retrofit = apiConnection.createRetrofit()

        return@lazy retrofit.create(classz)
    }

}