package br.com.redcode.easyreftrofit.samplekotlin.data.payload

import br.com.redcode.easyreftrofit.samplekotlin.data.model.DogImage
import br.com.redcode.easyreftrofit.samplekotlin.network.infra.Payload

/*
    CREATED BY @PEDROFSN
*/

data class ResponseGETDogImage(
    val message: String?, // https://images.dog.ceo/breeds/pyrenees/n02111500_1233.jpg
    val status: String? // success
) : Payload<DogImage> {
    override fun toModel() = DogImage(
        image = message ?: "",
        status = status ?: ""
    )
}