package com.strv.mlkitdemo.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

val Context.executor: Executor
    get() = ContextCompat.getMainExecutor(this)

fun Context.openAppSettings() = startActivity(
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
    }
)