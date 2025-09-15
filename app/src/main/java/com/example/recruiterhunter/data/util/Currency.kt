package com.example.recruiterhunter.data.util

enum class Currency(val label: String) {
    USD("$"),
    EUR("€"),
    RUB("₽"),
    RUR("₽"),
    AZN("₼"),
    BYR("br"),
    GEL("₾"),
    KGS("с"),
    KZT("₸"),
    UAH("₴"),
    UZS("so\'m")
}

fun String?.toCurrencySymbol(): String {
    return this?.let { code ->
        enumValues<Currency>().find { it.name == code }?.label ?: NOTHING
    } ?: NOTHING
}

private const val NOTHING: String = ""