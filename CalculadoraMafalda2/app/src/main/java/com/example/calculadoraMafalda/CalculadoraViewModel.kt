package com.example.calculadoraMafalda

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculadoraViewModel: ViewModel() {

    var state by mutableStateOf(CalculadoraState())
        private set

    fun onAction(action: CalculadoraAction){
        when (action) {
            is CalculadoraAction.Number -> enterNumero(action.number)
            is CalculadoraAction.Decimal -> enterDecimal()
            is CalculadoraAction.Clear -> state = CalculadoraState()
            is CalculadoraAction.Operation -> enterOperacao(action.operation)
            is CalculadoraAction.Calculate -> performCalculadora()
            is CalculadoraAction.Delete -> performDelete()
        }
    }

    private fun enterNumero(number: Int) {
        if (state.operation == null){
            if(state.number1.length >= MAX_NUM_LENGTH){
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if (state.number2.length >= MAX_NUM_LENGTH){
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }
    companion object{
        private const val MAX_NUM_LENGTH = 8
    }

    private fun enterDecimal() {
        if(state.operation == null && !state.number1.contains(".")
            && state.number1.isNotBlank()
            ){
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }
        if(!state.number2.contains(".")
            && state.number2.isNotBlank()
        ){
            state = state.copy(
                number1 = state.number2 + "."
            )
        }
    }

    private fun enterOperacao(operation: CalculadoraOperation) {
        if (state.number1.isNotBlank())
            state = state.copy(operation=operation)
    }

    private fun performCalculadora() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null){
            val result = when(state.operation){
                is CalculadoraOperation.Add -> number1 + number2
                is CalculadoraOperation.Subtract -> number1 - number2
                is CalculadoraOperation.Multiply -> number1 * number2
                is CalculadoraOperation.Divide -> number1 / number2
                null -> return
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }

    }

    private fun performDelete() {
        when{
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }

    }
}