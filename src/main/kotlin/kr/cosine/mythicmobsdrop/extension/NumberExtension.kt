package kr.cosine.mythicmobsdrop.extension

import java.text.DecimalFormat

private const val DEFAULT_FORMAT = "#,###.#########"
private val decimalFormat = DecimalFormat(DEFAULT_FORMAT)

fun Double.format(): String = decimalFormat.format(this)