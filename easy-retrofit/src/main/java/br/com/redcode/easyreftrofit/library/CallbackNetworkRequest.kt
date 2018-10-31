package br.com.redcode.easyreftrofit.library

import br.com.redcode.easyreftrofit.library.model.ErrorHandled


/**
 * Created by pedrofsn on 21/02/18.
 */
interface CallbackNetworkRequest {

    fun onNetworkHttpError(errorHandled: ErrorHandled)
    fun onNetworkTimeout()
    fun onNetworkError()
    fun onNetworkUnknownError(message: String)

}