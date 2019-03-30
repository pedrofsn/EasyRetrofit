package br.com.redcode.easyreftrofit.sample.rest.abstracts

import br.com.redcode.easyreftrofit.library.APIConnection
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
        interceptor: AbstractProxyInterceptor
) {

    abstract val classz: Class<T>
    private val moshi by lazy { MoshiConverterFactory.create() }

    val service: T by lazy {
        val okHttppBuilder = OkHttpClient().newBuilder()
        okHttppBuilder.connectTimeout(1, TimeUnit.MINUTES)
        okHttppBuilder.readTimeout(1, TimeUnit.MINUTES)
        okHttppBuilder.addInterceptor(interceptor)

        val logging = HttpLoggingInterceptor()
        logging.level = if (showLogs) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        okHttppBuilder.addInterceptor(logging)

        val apiConnection: APIConnection = APIConnection(
                apiBaseURL = baseURL,
                okHttppBuilder = okHttppBuilder,
                defaultConverterFactory = moshi
        )

        apiConnection.buiderRetrofit.addCallAdapterFactory(CoroutineCallAdapterFactory())
        val retrofit = apiConnection.createRetrofit()

        return@lazy retrofit.create(classz)
    }

}