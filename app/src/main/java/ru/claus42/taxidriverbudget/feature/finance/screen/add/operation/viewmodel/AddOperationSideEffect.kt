package ru.claus42.taxidriverbudget.feature.finance.screen.add.operation.viewmodel

sealed class AddOperationSideEffect {
    data object AddNewOperation : AddOperationSideEffect()
}