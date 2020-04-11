package br.com.redcode.easyreftrofit.samplekotlin.network.infra

import br.com.redcode.easyreftrofit.samplekotlin.BuildConfig


abstract class CustomAPIConnection<T> : AbstractAPIConnection<T>(
    baseURL = BuildConfig.API_BASE_URL,
    showLogs = BuildConfig.SHOW_LOGS,
    interceptor = ProxyInterceptor()
)