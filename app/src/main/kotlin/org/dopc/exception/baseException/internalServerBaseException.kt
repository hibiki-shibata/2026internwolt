package org.dopc.exception.server

import io.ktor.http.*

class InternalServerBaseException(
    val code: HttpStatusCode? = HttpStatusCode.InternalServerError,
    override val message: String = "Internal Server Error occurred"
) : RuntimeException("Server error occurred: code=$code, message=$message")