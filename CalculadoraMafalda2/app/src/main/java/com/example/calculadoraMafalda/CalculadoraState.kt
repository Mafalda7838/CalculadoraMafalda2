package com.example.calculadoraMafalda

data class CalculadoraState(
    val number1: String = "",
    val number2: String = "",
    val operation: CalculadoraOperation? = null

)
