package com.example.recruiterhunter.data.converters.vacancy.full

import com.example.recruiterhunter.data.dto.vacancies.response.details.VacancyDetailsResponseDto
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.entity.VacancyEntity
import com.example.recruiterhunter.data.util.toCurrencySymbol
import domain.model.vacancy.details.Vacancy

class VacanciesDetailsConverterImpl() : VacanciesDetailsConverter {
    override fun map(dto: VacancyDetailsResponseDto): Vacancy {
        return Vacancy(
            vacancyId = dto.id,
            vacancyName = dto.name,
            employerName = dto.employer.name,
            employerLogo = dto.employer.logo?.path ?: "",
            address = dto.address?.raw ?: dto.area.name,
            salaryFrom = dto.salary?.from?.toString() ?: "",
            salaryTo = dto.salary?.to?.toString() ?: "",
            currency = dto.salary?.currency.toCurrencySymbol(),
            employmentForm = dto.employmentForm?.name ?: "",
            workFormat = dto.workFormat?.let {
                it.map { workFormatDto -> workFormatDto.name }
            } ?: emptyList(),
            experience = dto.experience?.name ?: "",
            linkUrl = dto.linkUrl,
            description = dto.description,
            keySkills = dto.keySkills.map { keySkillsDto -> keySkillsDto.name },
        )
    }

    override fun mapToEntity(vacancy: Vacancy): VacancyEntity {
        return VacancyEntity(
            id = vacancy.vacancyId,
            vacancyName = vacancy.vacancyName,
            employerName = vacancy.employerName,
            employerLogo = vacancy.employerLogo,
            address = vacancy.address,
            salaryFrom = vacancy.salaryFrom,
            salaryTo = vacancy.salaryTo,
            currency = vacancy.currency,
            employmentForm = vacancy.employmentForm,
            workFormat = vacancy.workFormat,
            experience = vacancy.experience,
            linkUrl = vacancy.linkUrl,
            description = vacancy.description,
            keySkills = vacancy.keySkills,
            additionTime = System.currentTimeMillis()
        )
    }

    override fun map(vacancyEntity: VacancyEntity): Vacancy {
        return Vacancy(
            vacancyId = vacancyEntity.id,
            vacancyName = vacancyEntity.vacancyName,
            employerName = vacancyEntity.employerName,
            employerLogo = vacancyEntity.employerLogo,
            address = vacancyEntity.address,
            salaryFrom = vacancyEntity.salaryFrom,
            salaryTo = vacancyEntity.salaryTo,
            currency = vacancyEntity.currency,
            employmentForm = vacancyEntity.employmentForm,
            workFormat = vacancyEntity.workFormat,
            experience = vacancyEntity.experience,
            linkUrl = vacancyEntity.linkUrl,
            description = vacancyEntity.description,
            keySkills = vacancyEntity.keySkills,
            isFavorite = true
        )
    }
}