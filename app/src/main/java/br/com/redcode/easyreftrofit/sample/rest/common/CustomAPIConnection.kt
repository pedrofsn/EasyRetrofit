package br.com.redcode.easyreftrofit.sample.rest.common

import br.com.redcode.easyreftrofit.sample.BuildConfig
import br.com.redcode.easyreftrofit.sample.rest.abstracts.AbstractAPIConnection


abstract class CustomAPIConnection<T> : AbstractAPIConnection<T>(
    baseURL = BuildConfig.API_BASE_URL,
    showLogs = BuildConfig.SHOW_LOGS,
    interceptor = ProxyInterceptor()
)