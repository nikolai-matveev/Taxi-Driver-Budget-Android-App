package ru.claus42.taxidriverbudget.domain.model

enum class CategoryType {
    CASH, NON_CASH, TRANSFER, TIPS, // income
    FUEL, FOOD, OTHER, MILEAGE,     // expense
}