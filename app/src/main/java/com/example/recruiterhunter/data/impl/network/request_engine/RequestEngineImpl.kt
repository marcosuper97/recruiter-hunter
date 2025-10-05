package com.example.recruiterhunter.data.impl.network.request_engine

import com.example.recruiterhunter.domain.model.exceptions.AppException
import com.example.recruiterhunter.domain.services.network_check.NetworkCheckService
import com.example.recruiterhunter.domain.services.request_engine.RequestEngine
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RequestEngineImpl(private val networkCheckService: NetworkCheckService) : RequestEngine {
    override suspend fun <T> doRequest(
        block: suspend () -> T
    ): Result<T> {
        if (!networkCheckService.isInternetAvailable()) {
            return Result.failure(
                AppException.InternetHasNotAvailable(
                    "Отсутствует соединение с интернетом.",
                    Exception("Отсутствие соединения: NetworkCheck вернул false")
                )
            )
        }
        return try {
            Result.success(block())
        } catch (e: IOException) {
            when {
                e is SocketTimeoutException -> Result.failure(
                    AppException.NetworkError(
                        "Ошибка соединения: Socket timeout",
                        e
                    )
                )

                e is UnknownHostException -> Result.failure(
                    AppException.NetworkError(
                        "Ошибка хоста: unknown host exception",
                        e
                    )
                )

                else -> Result.failure(
                    AppException.UnknownException(
                        "Неизвестная ошибка: IOException",
                        e
                    )
                )
            }
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> Result.failure(
                    AppException.AuthorizationError(
                        "Ошибка авторизации.",
                        e
                    )
                )

                404 -> Result.failure(
                    AppException.EmptyResult(
                        "Ничего не найдено.",
                        e
                    )
                )

                in 400..499 -> Result.failure(
                    AppException.ClientError(
                        "Ошибка клиента (${e.code()})",
                        e
                    )
                )

                in 500..599 -> Result.failure(
                    AppException.ServerError(
                        "Ошибка сервера (${e.code()})",
                        e
                    )
                )

                else -> Result.failure(
                    AppException.UnknownException(
                        "HTTP ошибка: ${e.code()}",
                        e
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(
                AppException.UnknownException(
                    "Неизвестная ошибка.",
                    e
                )
            )
        }
    }
}