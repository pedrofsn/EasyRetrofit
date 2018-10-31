package br.com.redcode.easyreftrofit.library

import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by pedrofsn on 21/02/18.
 */
abstract class AbstractNetworkAndErrorHandler {

    abstract val callbackNetworkRequest: CallbackNetworkRequest?

    open fun handle(exception: Throwable) {
        when (exception) {
            is HttpException -> onNetworkHttpError(exception)
            is SocketTimeoutException -> callbackNetworkRequest?.onNetworkTimeout()
            is IOException -> callbackNetworkRequest?.onNetworkError()
            is UnknownHostException -> callbackNetworkRequest?.onNetworkError()
            else -> {
                callbackNetworkRequest?.onNetworkUnknownError("Opss...")
                catchedException(exception)
            }
        }
    }

    open fun catchedException(exception: Throwable) {

    }

    private fun onNetworkHttpError(httpException: HttpException) {
        val networkError = httpException.code()
        val errorBody = httpException.response().errorBody()?.string()
        errorBody?.let { handleErrorJSONWithStatusCodeHTTP(it, networkError) }
    }

    fun handleErrorJSONWithStatusCodeHTTP(errorBody: String, statusCode: Int) {
        try {
            val errorHandled = parseBodyError(errorBody, statusCode)
            callbackNetworkRequest?.onNetworkHttpError(errorHandled)
        } catch (e: Exception) {
            val errorHandled = ErrorHandled(
                message = e.toString(),
                networkError = statusCode
            )
            callbackNetworkRequest?.onNetworkHttpError(errorHandled)
        }
    }

    abstract fun parseBodyError(errorBodyAsString: String, networkError: Int): ErrorHandled
}