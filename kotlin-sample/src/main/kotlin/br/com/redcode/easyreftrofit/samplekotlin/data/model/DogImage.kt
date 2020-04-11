package br.com.redcode.easyreftrofit.samplekotlin.data.model

import br.com.redcode.easyreftrofit.samplekotlin.data.payload.ResponseGETDogImage


/*
    CREATED BY @PEDROFSN
*/

data class DogImage(
    val image: String,
    val status: String
) {
    fun toPayload() = ResponseGETDogImage(
        status = status,
        message = image
    )
}