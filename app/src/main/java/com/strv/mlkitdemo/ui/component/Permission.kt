package com.strv.mlkitdemo.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.strv.mlkitdemo.ext.openAppSettings

@ExperimentalPermissionsApi
@Composable
fun RequirePermission(
    permission: String,
    permissionNotAvailableContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {
    val permissionState = rememberPermissionState(permission)
    LaunchedEffect(Unit) { permissionState.launchPermissionRequest() }

    if (permissionState.status.isGranted) {
        content()
    } else {
        permissionNotAvailableContent()
    }
}

@Composable
fun MissingCameraPermissionContent(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text("No camera permission!")
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { context.openAppSettings() }
        ) {
            Text("Allow camera")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}