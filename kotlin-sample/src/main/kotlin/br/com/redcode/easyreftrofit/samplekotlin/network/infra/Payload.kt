package br.com.redcode.easyreftrofit.samplekotlin.network.infra

interface Payload<T> {
    fun toModel(): T
}