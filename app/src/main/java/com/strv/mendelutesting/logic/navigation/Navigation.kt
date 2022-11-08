package com.strv.mendelutesting.logic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.strv.mendelutesting.ui.dashboard.DashboardScreen
import com.strv.mendelutesting.ui.dashboard.DashboardViewModel
import com.strv.mendelutesting.ui.fail.FailScreen
import com.strv.mendelutesting.ui.report.ReportScreen
import com.strv.mendelutesting.ui.success.SuccessScreen

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
            DashboardScreen(onReportClick = { navController.navigate(AppScreens.Report.route) })
        }

        composable(AppScreens.Report.route) {
            ReportScreen(onSendReportClick = { navController.navigate(AppScreens.Fail.route) })
        }

        composable(AppScreens.Success.route) {
            SuccessScreen(onCloseClick = { navController.popBackStack() })
        }

        composable(AppScreens.Fail.route) {
            FailScreen(onCloseClick = { navController.popBackStack() })
        }
    }
}
