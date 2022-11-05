package com.strv.mendelutesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.strv.mendelutesting.ui.theme.MendeluTestingTheme

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
