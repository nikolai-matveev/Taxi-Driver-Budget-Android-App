package ru.claus42.taxidriverbudget.feature.finance.screen.main.model

data class AggregatedFinanceState(
    val headerBlock: HeaderBlock,
    val periodType: PeriodType,
    val chartInfos: List<ChartInfo>,
    val operationBlock: OperationBlock,
)
