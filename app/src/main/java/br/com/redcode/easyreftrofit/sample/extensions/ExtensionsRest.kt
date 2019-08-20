package br.com.redcode.easyreftrofit.sample.extensions

import br.com.redcode.base.extensions.extract
import br.com.redcode.base.interfaces.Payload
import br.com.redcode.base.rest.PayloadError
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import br.com.redcode.easyreftrofit.sample.rest.abstracts.BaseInteractor
import br.com.redcode.easyreftrofit.sample.rest.abstracts.NetworkAndErrorHandler
import br.com.redcode.easyreftrofit.sample.rest.common.API
import br.com.redcode.easyreftrofit.sample.rest.common.APIConnection
import retrofit2.HttpException
import java.net.UnknownHostException

/*
    CREATED BY @PEDROFSN
*/

fun BaseInteractor.api(): API = APIConnection.service

fun PayloadError.toModel(networkError: Int) = ErrorHandled(
    message = extract safe msg,
    actionAPI = extract safe acao,
    networkError = networkError,
    id = extract safe id
)

suspend fun <TypePayload : Payload<TypeModel>, TypeModel> TypePayload.doRequest(
    callbackNetworkRequest: CallbackNetworkRequest? = null,
    handleErrorManual: ((String?) -> Unit)? = null,
    handleFailureManual: ((Throwable) -> Unit)? = null
): TypeModel? {
    return try {
        val model = toModel()
        model
    } catch (e: Exception) {
        if (e is HttpException) {
            val code = e.code()
            val errorBody = e.response()?.errorBody()?.string()

            when {
                handleErrorManual == null && errorBody != null -> {
                    NetworkAndErrorHandler(
                        callbackNetworkRequest
                    ).handleErrorJSONWithStatusCodeHTTP(errorBody, code)
                }

                handleErrorManual != null -> {
                    handleErrorManual.invoke(errorBody)
                }

                handleFailureManual != null -> {
                    handleFailureManual.invoke(e)
                }

                else -> {
                    NetworkAndErrorHandler(callbackNetworkRequest).handle(e)
                    e.printStackTrace()
                }
            }
        } else if (e is UnknownHostException) {
            throw e
        } else {
            e.printStackTrace()
        }

        return null
    }
}