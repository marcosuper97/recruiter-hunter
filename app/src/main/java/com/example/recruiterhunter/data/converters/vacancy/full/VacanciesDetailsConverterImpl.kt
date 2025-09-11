package com.example.recruiterhunter.data.converters.vacancy.full

import com.example.recruiterhunter.data.dto.vacancies.response.details.VacancyDetailsResponseDto
import com.example.recruiterhunter.data.local.vacany.entity.VacancyEntity
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
            currency = dto.salary?.currency ?: "",
            employmentForm = dto.employmentForm?.name ?: "",
            workFormat = dto.workFormat?.let {
                it.map { workFormatDto -> workFormatDto.name }
            } ?: emptyList(),
            experience = dto.experience?.name ?: "",
            linkUrl = dto.linkUrl,
            description = dto.description,
            keySkills = dto.keySkills.map { keySkillsDto ->  keySkillsDto.name},
        )
    }

    override fun mapToEntity(vacancy: Vacancy): VacancyEntity {
        TODO("Not yet implemented")
    }

    override fun map(vacancyEntity: VacancyEntity): Vacancy {
        TODO("Not yet implemented")
    }
}