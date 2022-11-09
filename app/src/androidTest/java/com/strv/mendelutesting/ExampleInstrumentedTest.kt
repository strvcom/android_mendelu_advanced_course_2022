package com.strv.mendelutesting

import android.content.Context
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.strv.mendelutesting.ui.MainActivity
import com.strv.mendelutesting.ui.report.ReportScreen
import com.strv.mendelutesting.ui.report.ReportViewModel
import com.strv.mendelutesting.ui.report.TEST_TAG_REPORT_EMAIL_INPUT
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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

	private fun launchReportScreen() {
		composeRule.setContent {
			val reportViewModel = initReportViewModel() // inaccessible by design
			MaterialTheme {
				ReportScreen(
					viewModel = reportViewModel,
					onSendReportClick = {}
				)
			}
		}
	}

	private fun initReportViewModel(): ReportViewModel {
		return composeRule.activity.viewModels<ReportViewModel>().value
	}
}