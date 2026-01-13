package org.dopc.exception

import io.ktor.http.*

class InvalidClientParamException(
    override val message: String = "Invalid parameter provided by client"
) : HttpBaseException(code = HttpStatusCode.BadRequest, message = message)