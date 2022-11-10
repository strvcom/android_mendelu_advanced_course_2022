package com.strv.mendelutesting

import android.content.Context
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.strv.mendelutesting.data.WeatherType
import com.strv.mendelutesting.logic.navigation.AppScreens
import com.strv.mendelutesting.logic.navigation.Navigation
import com.strv.mendelutesting.ui.MainActivity
import com.strv.mendelutesting.ui.report.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@ExperimentalCoroutinesApi
@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UITests {

    private val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var navController: NavHostController

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    // Example UI test that composable is displayed
    @Test
    fun example_test() {
        launchReportScreen()
        with(composeRule) {
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
        }
    }

    /**
     *
     *
     *  TODO - More UI tests coming soon...
     *
     *
     * */

    //  Launch Report screen through Navigation
    private fun launchReportScreenWithNavigation() {
        composeRule.setContent {
            MaterialTheme {
                navController = rememberNavController()
                Navigation(
                    navController = navController,
                    startDestination = AppScreens.Report.route
                )
            }
        }
    }

    //  Launch only Report screen
    private fun launchReportScreen() {
        composeRule.setContent {
            val reportViewModel = initReportViewModel() // Explain: inaccessible by design
            MaterialTheme {
                ReportScreen(
                    viewModel = reportViewModel, // Explain: this line can be deleted
                    navigateToSuccessScreen = {},
                    navigateToFailScreen = {},
                )
            }
        }
    }

    private fun initReportViewModel(): ReportViewModel {
        return composeRule.activity.viewModels<ReportViewModel>().value
    }
}