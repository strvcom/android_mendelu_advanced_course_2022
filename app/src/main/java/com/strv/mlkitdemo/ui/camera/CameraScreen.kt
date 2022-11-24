package com.strv.mlkitdemo.ui.camera

import android.Manifest
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.objects.ObjectDetector
import com.strv.mlkitdemo.ext.executor
import com.strv.mlkitdemo.ui.component.CameraOverlay
import com.strv.mlkitdemo.ui.component.CameraPreview
import com.strv.mlkitdemo.ui.component.MissingCameraPermissionContent
import com.strv.mlkitdemo.ui.component.RequirePermission


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
) {
    // Request permission for CAMERA
    RequirePermission(
        permission = Manifest.permission.CAMERA,
        permissionNotAvailableContent = { MissingCameraPermissionContent() },
        content = {
            CameraScreenContent(
                modifier = modifier,
            )
        }
    )
}

@Composable
private fun CameraScreenContent(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    var detectedObjects by remember { mutableStateOf(listOf<DetectedObject>()) }


    Box(modifier = modifier.fillMaxSize()) {
        CameraPreview(
            modifier = Modifier,
            analyzer = MlKitAnalyzer(
                /* detectors = */ listOf(),
                /* targetCoordinateSystem = */ CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED,
                /* executor = */ context.executor
            ) { result ->
                detectedObjects = emptyList()
            }
        )
        CameraOverlay(detectedObjects)
    }
}