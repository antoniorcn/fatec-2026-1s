package edu.curso.estoque

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AppTeste() {
    Column {
        Text(
            "Hello World KMP - Laboratório de Desenvolvimento Multiplataforma",
            overflow = TextOverflow.Ellipsis,
            color = Color.Blue
        )

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Nome") },
            placeholder = { Text("Digite o nome completo") }
        )

        Button(onClick = { println("Salvo") }) {
            Text("Salvar")
        }
    }
}