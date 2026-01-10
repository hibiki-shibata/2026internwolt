class ClientException(
    val statusCode: Int,
    val body: String
) : RuntimeException("ClientException: statusCode=$statusCode, message=$body")