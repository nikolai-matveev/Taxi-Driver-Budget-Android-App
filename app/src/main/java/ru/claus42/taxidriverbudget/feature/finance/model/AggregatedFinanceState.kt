package ru.claus42.taxidriverbudget.feature.finance.model

data class AggregatedFinanceState(
    val headerBlock: HeaderBlock,
    val periodType: PeriodType,
    val chartInfos: List<ChartInfo>,
    val operationBlock: OperationBlock,
)
