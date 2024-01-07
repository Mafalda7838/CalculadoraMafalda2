package com.example.calculadoraMafalda

sealed class CalculadoraAction{
    data class Number(val number: Int): CalculadoraAction()
    object Clear: CalculadoraAction()
    object Delete: CalculadoraAction()
    object Decimal: CalculadoraAction()
    object Calculate: CalculadoraAction()
    data class Operation(val operation: CalculadoraOperation): CalculadoraAction()

}

