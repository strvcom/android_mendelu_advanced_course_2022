package com.strv.mlkitdemo.ui.gallery

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.objects.ObjectDetector
import com.strv.mlkitdemo.ext.bitmap

@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier,
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var detectedObjects by remember { mutableStateOf<List<DetectedObject>>(emptyList()) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
    }

    val bitmap by bitmap(imageUri = imageUri)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap!!.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.width(400.dp),
                contentScale = ContentScale.Inside
            )
        } else {
            Box(
                modifier = Modifier
                    .background(Color.Gray.copy(alpha = 0.3f))
                    .size(400.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .background(Color.Gray.copy(alpha = 0.3f))
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Results:")

            detectedObjects.firstOrNull()?.labels?.forEach { label ->
                Text(text = "${label.text} - ${label.confidence}")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { launcher.launch("image/*") })
            {
                Text(text = "Pick photo")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    if (bitmap != null) {
                        val inputImage = InputImage.fromBitmap(bitmap!!, 0)
                        // TODO use detector to process the image
                    }
                }) {
                Text(text = "Analyze")
            }
        }
    }
}

