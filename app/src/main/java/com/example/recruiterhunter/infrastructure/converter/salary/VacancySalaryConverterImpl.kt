package com.example.recruiterhunter.infrastructure.converter.salary

import android.content.Context
import com.example.recruiterhunter.R
import com.example.recruiterhunter.data.dto.vacancies.elements.SalaryDto
import com.example.recruiterhunter.data.util.toCurrencySymbol

class VacancySalaryConverterImpl(private val context: Context) :
    VacancySalaryConverter {
    override fun map(salary: SalaryDto?): String {
        if (salary != null) {
            val (salaryFrom, salaryTo, currency) = salary
            return when {
                salaryFrom != null && salaryTo != null -> context.getString(
                    R.string.salary_from_to,
                    salaryFrom,
                    salaryTo,
                    currency.toCurrencySymbol()
                )

                salaryFrom != null && salaryTo == null -> context.getString(
                    R.string.salary_from,
                    salaryFrom,
                    currency.toCurrencySymbol()
                )

                salaryFrom == null && salaryTo != null -> context.getString(
                    R.string.salary_to,
                    salaryTo,
                    currency.toCurrencySymbol()
                )

                else -> context.getString(R.string.salary_no_specified)
            }
        } else return context.getString(R.string.salary_no_specified)
    }
}