package com.example.recruiterhunter.domain.model.exceptions

sealed class AppException(
    open val logMessage: String,
    open val originalCause: Throwable?
) : Exception(logMessage, originalCause) {

    data class InternetHasNotAvailable(
        override val logMessage: String,
        override val originalCause: Throwable?
    ) : AppException(logMessage, originalCause)

    data class NetworkError(
        override val logMessage: String,
        override val originalCause: Throwable?
    ) : AppException(logMessage, originalCause)

    data class EmptyResult(
        override val logMessage: String,
        override val originalCause: Throwable?
    ) : AppException(logMessage, originalCause)

    data class AuthorizationError(
        override val logMessage: String,
        override val originalCause: Throwable?
    ) : AppException(logMessage, originalCause)

    data class ServerError(
        override val logMessage: String,
        override val originalCause: Throwable?
    ) : AppException(logMessage, originalCause)

    data class ClientError(
        override val logMessage: String,
        override val originalCause: Throwable?
    ) : AppException(logMessage, originalCause)

    data class HttpException(
        override val logMessage: String,
        override val originalCause: Throwable?
    ) : AppException(logMessage, originalCause)

    data class UnknownException(
        override val logMessage: String,
        override val originalCause: Throwable?
    ) : AppException(logMessage, originalCause)
}