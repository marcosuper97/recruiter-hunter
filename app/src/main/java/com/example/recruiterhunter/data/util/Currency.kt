package com.example.recruiterhunter.data.util

fun String?.toCurrencySymbol(): String {
    return this?.let { code ->
        CURRENCY_VALUES.find { it.name == code }?.label ?: NOTHING
    } ?: NOTHING
}

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

private val CURRENCY_VALUES = enumValues<Currency>()
private const val NOTHING: String = ""