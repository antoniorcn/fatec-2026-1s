package edu.curso.estoque

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EstoqueLista( vm : GestaoEstoqueViewModel,
                  paddingValues : PaddingValues = PaddingValues(10.dp) ) {

    Column( modifier = Modifier.padding( paddingValues ) ) {
        Text("Lista de Produtos")

        LazyColumn {
            items( vm.produtos ) { produto ->
                Card(
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    border = CardDefaults.outlinedCardBorder(true),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 5.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(0.7f)
                                .padding(horizontal = 15.dp, vertical = 5.dp)
                        ) {
                            Text("Categoria: ${produto.categoria}")
                            Text("Nome: ${produto.nome}")
                            Text("Estoque Minimo: ${produto.estoqueMinimo}")
                        }
                        Column( modifier = Modifier.fillMaxWidth(0.3f) ) {
                            IconButton(onClick = { vm.apagar(produto) }) {
                                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Apagar")
                            }
                            IconButton(onClick = { vm.editar(produto) }) {
                                Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Edit")
                            }
                        }
                    }
                }
            }
        }

    }
}