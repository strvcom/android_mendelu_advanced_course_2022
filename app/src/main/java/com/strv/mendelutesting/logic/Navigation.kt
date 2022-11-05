package com.strv.mendelutesting

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.strv.mendelutesting.logic.AppScreens
import com.strv.mendelutesting.ui.dashboard.DashboardScreen
import com.strv.mendelutesting.ui.report.ReportScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppScreens.Dashboard.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppScreens.Dashboard.route) {
            DashboardScreen(
                onReportClick = { navController.navigate(AppScreens.Report.route) }
            )
        }

        composable(AppScreens.Report.route) {
            ReportScreen()
        }
    }
}
