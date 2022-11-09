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
class ExampleInstrumentedTest {

    private val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

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
                hasText(targetContext.resources.getString(R.string.report_enter_email_invalid)),
                // TODO find out what is this for: useUnmergedTree = true
            ).assertDoesNotExist()
        }
    }

    @Test
    fun test_report_email_invalid() {
        launchReportScreen()
        with(composeRule) {
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).performTextInput("t@em@a@il.c@#@m")

            onNode(
                hasText(targetContext.resources.getString(R.string.report_enter_email_invalid)),
                useUnmergedTree = true
            ).assertExists()
        }
    }

    @Test
    fun test_report_screen_dropdown_visible() {
        launchReportScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).performTextInput("valid@email.com")
            onNodeWithTag(TEST_TAG_REPORT_DESCRIPTION_INPUT).performTextInput("some description")
            onNodeWithTag(TEST_TAG_REPORT_WEATHER_DROPDOWN_CLICKABLE_AREA).performClick()

            onNodeWithTag(TEST_TAG_REPORT_WEATHER_DROPDOWN).assertIsDisplayed()
        }
    }

    @Test
    fun test_report_screen_dropdown_changes_text() {
        launchReportScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).performTextInput("valid@email.com")
            onNodeWithTag(TEST_TAG_REPORT_DESCRIPTION_INPUT).performTextInput("some description")
            performDropdownSelection(WeatherType.SNOWING)

            onNode(
                hasText(WeatherType.SNOWING.displayName),
                useUnmergedTree = true
            ).assertIsDisplayed()
        }
    }

    @Test
    fun test_report_screen_button_disabled() {
        launchReportScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).performTextInput("valid@email.com")
            onNodeWithTag(TEST_TAG_REPORT_DESCRIPTION_INPUT).performTextInput("some description")

            onNodeWithTag(TEST_TAG_REPORT_BUTTON).assertIsNotEnabled()
        }
    }

    @Test
    fun test_report_screen_button_enabled() {
        launchReportScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).performTextInput("valid@email.com")
            onNodeWithTag(TEST_TAG_REPORT_DESCRIPTION_INPUT).performTextInput("some description")
            performDropdownSelection(WeatherType.STORM)

            onNodeWithTag(TEST_TAG_REPORT_BUTTON).assertIsEnabled()
        }
    }

    @Test
    fun test_report_screen_navigates_to_success_screen() {
        launchReportScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).performTextInput("valid@email.com")
            onNodeWithTag(TEST_TAG_REPORT_DESCRIPTION_INPUT).performTextInput("some description")
            performDropdownSelection(WeatherType.STORM)
            onNodeWithTag(TEST_TAG_REPORT_BUTTON).performClick()
            waitForIdle()

            val route = navController.currentBackStackEntry?.destination?.route
            assertTrue(route == AppScreens.Success.route)
        }
    }

    @Test
    fun test_report_screen_navigates_to_fail_screen() {
        launchReportScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).performTextInput("valid@email.com")
            onNodeWithTag(TEST_TAG_REPORT_DESCRIPTION_INPUT).performTextInput("some description")
            performDropdownSelection(WeatherType.RAINING_FISH_AND_FROGS)
            onNodeWithTag(TEST_TAG_REPORT_BUTTON).performClick()
            waitForIdle()

            val route = navController.currentBackStackEntry?.destination?.route
            assertTrue(route == AppScreens.Fail.route)
        }
    }

    private fun performDropdownSelection(weatherType: WeatherType) {
        composeRule.onNodeWithTag(TEST_TAG_REPORT_WEATHER_DROPDOWN_CLICKABLE_AREA).performClick()
        composeRule.onNode(
            hasText(weatherType.displayName),
            useUnmergedTree = true
        ).performClick()
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