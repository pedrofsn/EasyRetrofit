package br.com.redcode.easyreftrofit.samplekotlin.domain

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.samplekotlin.data.payload.PayloadError
import br.com.redcode.easyreftrofit.samplekotlin.network.impl.API
import br.com.redcode.easyreftrofit.samplekotlin.network.impl.APIConnection
import br.com.redcode.easyreftrofit.samplekotlin.network.impl.NetworkAndErrorHandler
import br.com.redcode.easyreftrofit.samplekotlin.network.infra.BaseInteractor
import br.com.redcode.easyreftrofit.samplekotlin.network.infra.Payload
import retrofit2.HttpException
import java.net.UnknownHostException
import br.com.redcode.easyreftrofit.library.model.ErrorHandled

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