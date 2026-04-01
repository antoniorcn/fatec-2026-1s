package edu.curso

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

actual fun Titulo(): @Composable (() -> Unit) = {
    Text("Android", fontSize = 32.sp)
}