package br.com.redcode.easyreftrofit.sample.rest.common

object APIConnection : CustomAPIConnection<API>() {
    override val classz = API::class.java
}