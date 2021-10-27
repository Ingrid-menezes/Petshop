package br.com.petshop.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun BigDecimal.brazilianCurrencyValue(): String {
    val formatter: NumberFormat = NumberFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this)
}