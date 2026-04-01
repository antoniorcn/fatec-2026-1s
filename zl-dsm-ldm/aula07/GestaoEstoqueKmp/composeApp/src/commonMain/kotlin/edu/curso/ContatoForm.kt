package edu.curso

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ContatoForm(vModel : ContatoViewModel,
                paddingValues : PaddingValues = PaddingValues(0.dp)) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues)
            .border(
                width = 1.dp, color = Color.Magenta,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(5.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Formulario de Contatos")
    }
}