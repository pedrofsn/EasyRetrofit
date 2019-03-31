package br.com.redcode.easyreftrofit.sample.domain

import br.com.redcode.base.activities.BaseActivity
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/*
    CREATED BY @PEDROFSN
*/

abstract class CustomActivity : BaseActivity(), CallbackNetworkRequest, CoroutineScope {

    val job = Job()
    override val coroutineContext: CoroutineContext
        get() = io()

    fun io() = job + Dispatchers.IO
    fun main() = job + Dispatchers.Main

    override fun onNetworkHttpError(errorHandled: ErrorHandled) = showMessage(errorHandled.message)
    override fun onNetworkTimeout() = showMessage("onNetworkTimeout")
    override fun onNetworkError() = showMessage("onNetworkError")
    override fun onNetworkUnknownError(message: String) = showMessage("onNetworkUnknownError: $message")

}