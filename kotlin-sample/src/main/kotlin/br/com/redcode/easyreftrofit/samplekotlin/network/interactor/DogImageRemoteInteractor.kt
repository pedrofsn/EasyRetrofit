package br.com.redcode.easyreftrofit.samplekotlin.network.interactor

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.samplekotlin.domain.api
import br.com.redcode.easyreftrofit.samplekotlin.domain.doRequest
import br.com.redcode.easyreftrofit.samplekotlin.network.infra.BaseInteractor


/*
    CREATED BY @PEDROFSN
*/

class DogImageRemoteInteractor(override val callbackNetworkRequest: CallbackNetworkRequest?) :
    BaseInteractor {

    suspend fun receiveRemovals() = api()
        .receiveDogImage()
        .doRequest(callbackNetworkRequest)

}