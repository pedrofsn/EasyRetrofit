package br.com.redcode.easyreftrofit.sample.data.model

import br.com.redcode.easyreftrofit.sample.data.payloads.ResponseGETDogImage

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