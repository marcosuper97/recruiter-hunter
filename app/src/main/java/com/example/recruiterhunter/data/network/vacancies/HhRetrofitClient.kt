package com.example.recruiterhunter.data.network.vacancies

import android.content.Context
import com.example.recruiterhunter.data.dto.vacancies.filteres.areas.AreaDto
import com.example.recruiterhunter.data.dto.vacancies.filteres.industry.IndustryGroupDto
import com.example.recruiterhunter.data.dto.vacancies.response.details.VacancyDetailsResponseDto
import com.example.recruiterhunter.data.dto.vacancies.response.preview.VacanciesResponseDto
import com.example.recruiterhunter.data.network.common.isInternetAvailable
import retrofit2.HttpException
import java.io.IOException

class HhRetrofitClient(
    private val context: Context,
    private val api: HhApi
) : HhNetworkClient {

    override suspend fun detailsVacancyRequest(requestId: String): Result<VacancyDetailsResponseDto> {
        return executeRequest { api.getVacancyDetails(requestId) }
    }

    override suspend fun vacanciesSearchRequest(requestQuery: Map<String, String>): Result<VacanciesResponseDto> {
        return executeRequest { api.searchVacancies(requestQuery) }
    }

    override suspend fun getAreas(): Result<List<AreaDto>> {
        return executeRequest { api.getAreas() }
    }

    override suspend fun getIndustries(): Result<List<IndustryGroupDto>> {
        return executeRequest { api.getIndustries() }
    }

    private suspend fun <T> executeRequest(block: suspend () -> T): Result<T> {
        if (!isInternetAvailable(context)) {
            return Result.failure(Hh)
        }
        return try {
            Result.success(block())
        } catch (e: IOException) {
            Result.failure(AppException.NoInternetConnection())
        } catch (e: HttpException) {
            Result.failure(
                when (e.code()) {
                    NOT_FOUND_CODE -> AppException.NotFound()
                    in SERVER_ERROR_CODE_MIN..SERVER_ERROR_CODE_MAX -> AppException.ServerError()
                    else -> AppException.UnknownException()
                }
            )
        } catch (a: AppException.UnknownException) {
            Result.failure(AppException.UnknownException())
        }
    }

    companion object {
        private const val NOT_FOUND_CODE = 404
        private const val SERVER_ERROR_CODE_MIN = 500
        private const val SERVER_ERROR_CODE_MAX = 599
    }
}