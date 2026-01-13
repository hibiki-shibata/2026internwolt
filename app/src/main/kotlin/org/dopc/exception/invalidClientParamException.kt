package org.dopc.exception.client

import io.ktor.http.HttpStatusCode

class InvalidClientParamException(
    override val message: String,
) : HttpClientExceptionBase(code = HttpStatusCode.BadRequest, message = "Invalid client parameter: $message")