package com.strv.mlkitdemo.ext

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

val Int.fromPxToDp: Dp
    get() = (this / Resources.getSystem().displayMetrics.density).roundToInt().dp
