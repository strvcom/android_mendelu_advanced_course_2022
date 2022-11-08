package com.strv.mendelutesting.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.strv.mendelutesting.R

@Composable
fun CloseButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = stringResource(id = R.string.close))
    }
}

@Preview
@Composable
private fun CloseButton_Preview() {
    CloseButton(onClick = {})
}
