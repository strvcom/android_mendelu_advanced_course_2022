package com.strv.mlkitdemo

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.google.mlkit.vision.objects.ObjectDetector
import com.strv.mlkitdemo.ui.camera.CameraScreen
import com.strv.mlkitdemo.ui.gallery.GalleryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf(Screens.CAMERA) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        },
        content = { paddingValues ->
            Crossfade(targetState = currentScreen) {
                when (it) {
                    Screens.GALLERY -> GalleryScreen(
                        modifier = Modifier.padding(paddingValues)
                    )
                    Screens.CAMERA -> CameraScreen(
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.Camera),
                            contentDescription = ""
                        )
                    },
                    onClick = {
                        currentScreen = Screens.CAMERA
                    },
                    selected = currentScreen == Screens.CAMERA
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.Image),
                            contentDescription = ""
                        )
                    },
                    onClick = {
                        currentScreen = Screens.GALLERY
                    },
                    selected = currentScreen == Screens.GALLERY
                )
            }
        }
    )
}

enum class Screens {
    CAMERA,
    GALLERY
}