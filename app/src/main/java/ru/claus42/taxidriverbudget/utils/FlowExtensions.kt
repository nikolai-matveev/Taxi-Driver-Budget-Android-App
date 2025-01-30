package ru.claus42.taxidriverbudget.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

inline fun <reified T> List<Flow<T>>.flattenFlow(): Flow<List<T>> =
    combine(this@flattenFlow) { it.toList() }