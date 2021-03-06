package br.com.redcode.easyreftrofit.library.model

data class ErrorHandled(
    val message: String,
    val actionAPI: Int = -1,
    val networkError: Int = -1,
    val id: String = ""
)