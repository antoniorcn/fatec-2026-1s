package edu.curso.estoque

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EstoqueLista( vm : GestaoEstoqueViewModel,
                  paddingValues : PaddingValues = PaddingValues(10.dp) ) {

    Column( modifier = Modifier.padding( paddingValues ) ) {
        Text("Lista de Produtos")

        for ( produto in vm.produtos )  {
            Card(shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                border = CardDefaults.outlinedCardBorder(true),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical=5.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.7f)
                            .padding(horizontal = 15.dp, vertical = 5.dp)
                    ) {
                        Text("Categoria: ${produto.categoria}")
                        Text("Categoria: ${produto.nome}")
                        Text("Estoque Minimo: ${produto.estoqueMinimo}")
                    }
                    IconButton(modifier = Modifier.fillMaxWidth(0.3f),
                            onClick = { vm.apagar(produto) }) {
                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Apagar")
                    }
                }
            }
        }

    }
}