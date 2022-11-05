package com.strv.mendelutesting.logic.navigation

import androidx.annotation.StringRes
import com.strv.mendelutesting.R

sealed class AppScreens(
    val route: String,
    @StringRes val name: Int,
) {

    object Dashboard : AppScreens(
        route = "dashboard",
        name = R.string.dashboard_title
    )

    object Report : AppScreens(
        route = "report",
        name = R.string.report_title
    )

}