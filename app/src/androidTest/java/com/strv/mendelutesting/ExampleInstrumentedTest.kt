package com.strv.mendelutesting

import android.content.Context
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.strv.mendelutesting.logic.navigation.AppScreens
import com.strv.mendelutesting.logic.navigation.Navigation
import com.strv.mendelutesting.ui.MainActivity
import com.strv.mendelutesting.ui.report.ReportScreen
import com.strv.mendelutesting.ui.report.ReportViewModel
import com.strv.mendelutesting.ui.report.TEST_TAG_REPORT_BUTTON
import com.strv.mendelutesting.ui.report.TEST_TAG_REPORT_EMAIL_INPUT
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
class ExampleInstrumentedTest {

	val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var navController: NavHostController

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

	@get:Rule(order = 1)
	val composeRule = createAndroidComposeRule<MainActivity>() // TestReportActivity

	@Before
	fun setUp() {
		hiltRule.inject()
	}

	@Test
	fun test_report_email_valid() {
		launchReportScreen()
		with(composeRule) {
			onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
			onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).performTextInput("test@email.com")
			// Explain: Why not this?
			//composeRule.onNodeWithTag(TEST_TAG_REPORT_EMAIL_ERROR, useUnmergedTree = true).assertIsNotDisplayed()
			onNode(
				hasText(targetContext.resources.getString(R.string.invalid_email)),
				// TODO find out what is this for: useUnmergedTree = true
			).assertDoesNotExist()
		}
	}

	@Test
	fun test_report_email_invalid() {
		launchReportScreen()
		with(composeRule) {
			onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
			onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT)
				.performTextInput("t@em@a@il.c@#@om")
			onNode(
				hasText(targetContext.resources.getString(R.string.invalid_email)),
				useUnmergedTree = true
			).assertExists()
		}
	}

    @Test
    fun test_report_screen_navigates_to_success_screen() {
        launchReportScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TEST_TAG_REPORT_BUTTON).performClick()
            waitForIdle()

            val route = navController.currentBackStackEntry?.destination?.route
            assertTrue(route == AppScreens.Success.route)
        }
    }

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

    private fun launchReportScreen() {
		composeRule.setContent {
			val reportViewModel = initReportViewModel() // Explain: inaccessible by design
			MaterialTheme {
				ReportScreen(
					viewModel = reportViewModel, // Explain: this line can be deleted
					onSendReportClick = {}
				)
			}
		}
    }

    private fun initReportViewModel(): ReportViewModel {
        return  composeRule.activity.viewModels<ReportViewModel>().value
    }
}