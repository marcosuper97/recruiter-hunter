package com.example.recruiterhunter.core.exceptions

sealed class AppException(override val message: String) : Exception() {
    data class NetworkError(
        override val message: String
    ) : AppException(message)

    data class EmptyResult(
        override val message: String
    ) : AppException(message)

    data class AuthorizationError(
        override val message: String
    ) : AppException(message)

    data class ServerError(
        override val message: String
    ) : AppException(message)

    data class ClientError(
        override val message: String
    ) : AppException(message)

    data class HttpException(
        override val message: String
    ) : AppException(message)

    data class UnknownException(
        override val message: String
    ) : AppException(message)
}