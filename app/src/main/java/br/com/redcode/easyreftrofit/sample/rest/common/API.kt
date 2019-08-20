package br.com.redcode.easyreftrofit.sample.rest.common

import br.com.redcode.easyreftrofit.sample.data.payloads.ResponseGETDogImage
import retrofit2.http.GET

interface API {

    @GET("image/random")
    suspend fun receiveDogImage(): ResponseGETDogImage

}
