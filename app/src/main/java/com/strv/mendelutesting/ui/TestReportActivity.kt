package com.strv.mendelutesting.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.strv.mendelutesting.ui.report.ReportScreen
import dagger.hilt.android.AndroidEntryPoint

// TODO move to debug folder
@AndroidEntryPoint
class TestReportActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ReportScreen(
				onSendReportClick = {}
			)
		}
	}
}