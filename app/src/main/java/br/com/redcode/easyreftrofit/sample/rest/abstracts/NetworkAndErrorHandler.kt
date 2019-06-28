package br.com.redcode.easyreftrofit.sample.rest.abstracts

import br.com.redcode.base.extensions.extract
import br.com.redcode.base.extensions.toLogcat
import br.com.redcode.base.rest.PayloadError
import br.com.redcode.easyreftrofit.library.AbstractNetworkAndErrorHandler
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import br.com.redcode.easyreftrofit.sample.App
import br.com.redcode.easyreftrofit.sample.BuildConfig
import br.com.redcode.easyreftrofit.sample.R
import br.com.redcode.easyreftrofit.sample.extensions.toModel
import com.squareup.moshi.Moshi

class NetworkAndErrorHandler(override val callbackNetworkRequest: CallbackNetworkRequest?) :
    AbstractNetworkAndErrorHandler() {

    private val message by lazy { extract safe App.getContext()?.getString(R.string.error_http_server) }

    companion object {
        val moshi by lazy { Moshi.Builder().build() }
    }

    override fun catchedException(exception: Throwable) {
        when {
            BuildConfig.DEBUG.not() -> "Throwable message: ${exception.message}".toLogcat()
            else -> exception.printStackTrace()
        }
    }

    override fun parseBodyError(errorBodyAsString: String, networkError: Int): ErrorHandled {
        return try {
            val adapter = moshi.adapter(PayloadError::class.java)
            val payloadError: PayloadError? = adapter.fromJson(errorBodyAsString)
            val modelError = payloadError?.toModel(networkError)
            modelError ?: ErrorHandled(message = message, networkError = networkError)
        } catch (e: Exception) {
            val error = PayloadError(msg = String.format(message, networkError), msg_dev = e.message)
            val errorHandled = error.toModel(networkError)
            "Error in method 'parseBodyError' from class 'NetworkAndErrorHandler.kt': ${e.message}".toLogcat()
            errorHandled
        }
    }

}