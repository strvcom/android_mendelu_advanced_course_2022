package com.strv.mendelutesting.ui.fail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.strv.mendelutesting.R
import com.strv.mendelutesting.ui.components.CloseButton

const val CONTENT_FAIL = "content_success"
const val ICON_FAIL = "icon_success"
const val TEXT_FAIL = "text_success"

@Composable
fun FailScreen(onCloseClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                ContentSuccess()
            }
            CloseButton(onClick = onCloseClick)
        }
    }
}

@Composable
private fun BoxScope.ContentSuccess() {
    Column(
        modifier = Modifier
            .align(Alignment.Center)
            .testTag(CONTENT_FAIL),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp)
                .testTag(ICON_FAIL),
            painter = painterResource(id = R.drawable.ic_fail),
            contentDescription = stringResource(id = R.string.fail_icon_fail_description),
            tint = colorResource(id = R.color.fail)
        )
        Text(
            modifier = Modifier.testTag(TEXT_FAIL),
            text = stringResource(id = R.string.fail_title),
            style = MaterialTheme.typography.h5
        )
    }
}

@Preview
@Composable
private fun SuccessScreen_Preview() {
    FailScreen(onCloseClick = {})
}
