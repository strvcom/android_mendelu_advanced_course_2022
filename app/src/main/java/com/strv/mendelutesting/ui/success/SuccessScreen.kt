package com.strv.mendelutesting.ui.success

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

const val TEST_TAG_SUCCESS_CONTENT = "content_success"
const val TEST_TAG_SUCCESS_ICON = "icon_success"
const val TEST_TAG_SUCCESS_TEXT = "text_success"

@Composable
fun SuccessScreen(onCloseClick: () -> Unit) {
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
            .testTag(TEST_TAG_SUCCESS_CONTENT),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp)
                .testTag(TEST_TAG_SUCCESS_ICON),
            painter = painterResource(id = R.drawable.ic_success),
            contentDescription = stringResource(id = R.string.success_icon_success_description),
            tint = colorResource(id = R.color.success)
        )
        Text(
            modifier = Modifier.testTag(TEST_TAG_SUCCESS_TEXT),
            text = stringResource(id = R.string.success_title),
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun CloseButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = stringResource(R.string.close))
    }
}

@Preview
@Composable
private fun SuccessScreen_Preview() {
    SuccessScreen(onCloseClick = {})
}
