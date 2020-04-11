package br.com.redcode.easyreftrofit.samplekotlin.network.impl

import br.com.redcode.easyreftrofit.samplekotlin.network.infra.CustomAPIConnection

object APIConnection : CustomAPIConnection<API>() {
    override val classz = API::class.java
}