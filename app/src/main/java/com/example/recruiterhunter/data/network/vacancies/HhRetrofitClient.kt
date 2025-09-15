package com.example.recruiterhunter.data.network.vacancies

import android.content.Context
import com.example.recruiterhunter.R
import com.example.recruiterhunter.data.dto.vacancies.filteres.areas.AreaDto
import com.example.recruiterhunter.data.dto.vacancies.filteres.industry.IndustryGroupDto
import com.example.recruiterhunter.data.dto.vacancies.response.details.VacancyDetailsResponseDto
import com.example.recruiterhunter.data.dto.vacancies.response.preview.VacanciesResponseDto
import com.example.recruiterhunter.data.network.common.NetworkCheck
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
        if (!NetworkCheck.isInternetAvailable(context)) {
            return Result.failure(IOException("Отсутствует соединение с интернетом"))
        }
        return try {
            Result.success(block())
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: HttpException) {
            Result.failure(
                e
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}