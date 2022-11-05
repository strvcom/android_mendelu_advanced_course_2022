package com.strv.mendelutesting.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.strv.mendelutesting.logic.navigation.Navigation
import com.strv.mendelutesting.ui.theme.MendeluTestingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MendeluTestingTheme {
                Navigation()
            }
        }
    }
}
