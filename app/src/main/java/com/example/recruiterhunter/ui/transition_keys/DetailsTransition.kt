package com.example.recruiterhunter.ui.transition_keys

object DetailsTransition {
    const val EMPLOYER_LOGO: String = "employerLogo"
    const val EMPLOYER_NAME: String = "employerName"
    const val VACANCY_NAME: String = "vacancyName"
    const val VACANCY_ID: String = "vacancyId"
    const val SALARY: String = "salary"
    const val ADDRESS: String = "address"
    const val VACANCY_CONTAINER: String = "vacancy_container"

    fun vacancyIdKey(vacancyId: Long?): String = "${VACANCY_ID}_$vacancyId"
}