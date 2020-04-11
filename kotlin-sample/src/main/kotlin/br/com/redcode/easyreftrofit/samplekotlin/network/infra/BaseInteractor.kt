package br.com.redcode.easyreftrofit.samplekotlin.network.infra

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

/**
 * Created by pedrofsn on 03/02/18.
 */
interface BaseInteractor {
    val callbackNetworkRequest: CallbackNetworkRequest?
}

