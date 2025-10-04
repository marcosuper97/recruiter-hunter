package com.example.recruiterhunter.domain.model.exceptions

sealed class AppException(
    open val userMessage: String,
    open val originalCause: Throwable?
) : Exception(userMessage, originalCause) {

    data class NetworkError(
        override val userMessage: String,
        override val originalCause: Throwable?
    ) : AppException(userMessage, originalCause)

    data class EmptyResult(
        override val userMessage: String,
        override val originalCause: Throwable?
    ) : AppException(userMessage, originalCause)

    data class AuthorizationError(
        override val userMessage: String,
        override val originalCause: Throwable?
    ) : AppException(userMessage, originalCause)

    data class ServerError(
        override val userMessage: String,
        override val originalCause: Throwable?
    ) : AppException(userMessage, originalCause)

    data class ClientError(
        override val userMessage: String,
        override val originalCause: Throwable?
    ) : AppException(userMessage, originalCause)

    data class HttpException(
        override val userMessage: String,
        override val originalCause: Throwable?
    ) : AppException(userMessage, originalCause)

    data class UnknownException(
        override val userMessage: String,
        override val originalCause: Throwable?
    ) : AppException(userMessage, originalCause)
}