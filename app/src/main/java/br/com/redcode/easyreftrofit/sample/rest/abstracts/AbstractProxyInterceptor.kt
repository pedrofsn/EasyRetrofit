package br.com.redcode.easyreftrofit.sample.rest.abstracts

import br.com.redcode.base.extensions.extract
import br.com.redcode.base.mvvm.extensions.getAppVersionName
import br.com.redcode.easyreftrofit.sample.App
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by pedrofsn on 17/10/2017.
 */
abstract class AbstractProxyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()
        val httpUrlBuilder = originalHttpUrl.newBuilder()
        val httpUrl = httpUrlBuilder.build()

        val requestBuilder = original
                .newBuilder()
                .url(httpUrl)

        val token = "@pedrofsn123"

        requestBuilder.addHeader("Accept", "application/json")
        requestBuilder.addHeader("osversion", extract safe App.getContext()?.getAppVersionName())
        requestBuilder.addHeader("osname", "android")
        requestBuilder.addHeader("Authorization", "Bearer $token")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}