package com.strv.mendelutesting

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.strv.mendelutesting.screens.AppScreen
import com.strv.mendelutesting.screens.dashboard.DashboardScreen
import com.strv.mendelutesting.screens.report.ReportScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppScreen.Dashboard.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppScreen.Dashboard.route) {
            DashboardScreen(
                onReportClick = { navController.navigate(AppScreen.Report.route) }
            )
        }

        composable(AppScreen.Report.route) {
            ReportScreen()
        }
    }
}
