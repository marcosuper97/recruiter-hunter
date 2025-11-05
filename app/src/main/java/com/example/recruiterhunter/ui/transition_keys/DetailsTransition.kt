package com.example.recruiterhunter.ui.transition_keys

object DetailsTransition {
    const val EMPLOYER_LOGO: String = "employerLogo"
    const val EMPLOYER_NAME: String = "employerName"
    const val VACANCY_NAME: String = "vacancyName"
    const val VACANCY_ID: String = "vacancyId"
    const val SALARY: String = "salary"
    const val ADDRESS: String = "address"
    const val VACANCY_CONTAINER: String = "vacancy_container"
    const val NAVIGATION_ICON: String = "navigation_icon"

    fun vacancyIdKey(vacancyId: Long?): String = "${VACANCY_ID}_$vacancyId"
    fun containerKey(vacancyId: Long?): String = "${VACANCY_CONTAINER}_$vacancyId"
    fun vacancyName(vacancyId: Long?): String = "${VACANCY_NAME}_$vacancyId"
    fun employerName(vacancyId: Long?): String = "${EMPLOYER_NAME}_$vacancyId"
    fun address(vacancyId: Long?): String = "${ADDRESS}_$vacancyId"
    fun salary(vacancyId: Long?): String = "${SALARY}_$vacancyId"
    fun navigationIcon(vacancyId: Long?): String = "${NAVIGATION_ICON}_$vacancyId"
}