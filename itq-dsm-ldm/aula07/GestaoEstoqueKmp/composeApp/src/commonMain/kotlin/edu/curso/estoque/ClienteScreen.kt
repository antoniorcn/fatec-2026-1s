package edu.curso.estoque

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ClienteScreen( padding : PaddingValues ) {

    Column( modifier = Modifier.padding(padding) ) {

        Text( "Tela de Gestão de Clientes")

    }

}