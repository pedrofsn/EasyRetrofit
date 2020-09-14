package br.com.redcode.easyreftrofit.library

/*
    CREATED BY @PEDROFSN IN 24/12/2018 08:44
*/

interface Payload<T> {
    fun toModel(): T
}