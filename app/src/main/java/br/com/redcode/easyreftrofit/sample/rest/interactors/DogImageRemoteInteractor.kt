package br.com.redcode.easyreftrofit.sample.rest.interactors

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.sample.extensions.api
import br.com.redcode.easyreftrofit.sample.extensions.doRequest
import br.com.redcode.easyreftrofit.sample.rest.abstracts.BaseInteractor

/*
    CREATED BY @PEDROFSN
*/

class DogImageRemoteInteractor(override val callbackNetworkRequest: CallbackNetworkRequest?) : BaseInteractor {

    suspend fun receiveRemovals() = api()
        .receiveDogImage()
        .doRequest(callbackNetworkRequest)

}