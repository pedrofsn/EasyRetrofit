package br.com.redcode.retrofit.library

import br.com.redcode.retrofit.library.model.ErrorHandled


/**
 * Created by pedrofsn on 21/02/18.
 */
interface CallbackNetworkRequest {

    fun onNetworkHttpError(errorHandled: ErrorHandled)
    fun onNetworkTimeout()
    fun onNetworkError()
    fun onNetworkUnknownError(message: String)

}