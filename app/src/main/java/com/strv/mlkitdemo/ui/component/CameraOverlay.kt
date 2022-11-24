package com.strv.mlkitdemo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.mlkit.vision.objects.DetectedObject
import com.strv.mlkitdemo.ext.fromPxToDp

@Composable
fun CameraOverlay(objects: List<DetectedObject>) {
    Box(modifier = Modifier.fillMaxSize()) {
        objects.forEach { detectedObject ->
            val rect = detectedObject.boundingBox
            Box(
                modifier = Modifier
                    .size(
                        DpSize(
                            width = rect.width().fromPxToDp,
                            height = rect.height().fromPxToDp
                        )
                    )
                    .offset(
                        x = (rect.centerX() - rect.width() / 2).fromPxToDp,
                        y = (rect.centerY() - rect.height() / 2).fromPxToDp
                    )
                    .border(2.dp, Color.Red)
            )
            Column(
                modifier = Modifier
                    .offset(
                        x = (rect.centerX() - rect.width() / 2).fromPxToDp,
                        y = (rect.centerY() + rect.height() / 2).fromPxToDp
                    )
                    .background(color = Color.Black.copy(alpha = 0.3f))
            ) {
                detectedObject.labels.forEach {
                    Text(
                        text = "${it.text} - ${it.confidence}",
                        color = Color.Yellow,
                        fontSize = 16.sp
                    )
                }
            }

        }
    }
}