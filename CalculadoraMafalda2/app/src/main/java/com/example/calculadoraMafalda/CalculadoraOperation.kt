package com.example.calculadoraMafalda

sealed class CalculadoraOperation(val symbol: String){
    object Add: CalculadoraOperation("+")
    object Subtract: CalculadoraOperation("-")
    object Divide: CalculadoraOperation("/")
    object Multiply: CalculadoraOperation("*")
}
