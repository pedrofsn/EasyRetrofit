package br.com.redcode.easyreftrofit.samplekotlin.domain

import br.com.redcode.easyreftrofit.samplekotlin.network.infra.Payload

fun <T> List<Payload<T>>?.toModel(): List<T>? = this?.map { obj -> obj.toModel() }

fun String?.toLogcat() = this?.let { println(it) }