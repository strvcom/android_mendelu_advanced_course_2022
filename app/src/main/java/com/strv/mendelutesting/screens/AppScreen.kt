package com.strv.mendelutesting.screens

import androidx.annotation.StringRes
import com.strv.mendelutesting.R

sealed class AppScreen(
    val route: String,
    @StringRes val name: Int,
) {

    object Dashboard : AppScreen(
        route = "dashboard",
        name = R.string.dashboard_title
    )

    object Report : AppScreen(
        route = "report",
        name = R.string.report_title
    )

}