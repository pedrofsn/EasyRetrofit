package br.com.redcode.easyreftrofit.samplekotlin.network.impl

import br.com.redcode.easyreftrofit.samplekotlin.data.payload.ResponseGETDogImage
import retrofit2.http.GET

interface API {

    @GET("image/random")
    suspend fun receiveDogImage(): ResponseGETDogImage

}
