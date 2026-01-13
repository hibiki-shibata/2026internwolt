package org.dopc.exception.client

import io.ktor.http.*

class HttpClientBaseException(
    val code: HttpStatusCode? = HttpStatusCode.BadRequest,
    override val message: String = "HTTP Client Error occurred"
) : RuntimeException("Client error occurred: code=$code, message=$message")