package br.com.redcode.easyreftrofit.sample.data.payloads

import br.com.redcode.base.extensions.extract
import br.com.redcode.easyreftrofit.library.Payload
import br.com.redcode.easyreftrofit.sample.data.model.DogImage

/*
    CREATED BY @PEDROFSN
*/

data class ResponseGETDogImage(
        val message: String?, // https://images.dog.ceo/breeds/pyrenees/n02111500_1233.jpg
        val status: String? // success
) : Payload<DogImage> {
    override fun toModel() = DogImage(
            image = extract safe message,
            status = extract safe status
    )
}