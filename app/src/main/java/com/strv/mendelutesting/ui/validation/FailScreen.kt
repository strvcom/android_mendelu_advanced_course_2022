package com.strv.mendelutesting.ui.validation

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

const val TEST_TAG_FAIL_CONTENT = "content_success"
const val TEST_TAG_FAIL_ICON = "icon_success"
const val TEST_TAG_FAIL_TEXT = "text_success"

@Composable
fun FailScreen(onTryAgainClick: () -> Unit) {
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
            TryAgainButton(onClick = onTryAgainClick)
        }
    }
}

@Composable
private fun BoxScope.ContentSuccess() {
    Column(
        modifier = Modifier
            .align(Alignment.Center)
            .testTag(TEST_TAG_FAIL_CONTENT),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp)
                .testTag(TEST_TAG_FAIL_ICON),
            painter = painterResource(id = R.drawable.ic_fail),
            contentDescription = stringResource(id = R.string.fail_icon_fail_description),
            tint = colorResource(id = R.color.fail)
        )
        Text(
            modifier = Modifier.testTag(TEST_TAG_FAIL_TEXT),
            text = stringResource(id = R.string.fail_title),
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun TryAgainButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = stringResource(R.string.try_again))
    }
}

@Preview
@Composable
private fun SuccessScreen_Preview() {
    FailScreen(onTryAgainClick = {})
}
