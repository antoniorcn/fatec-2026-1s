package edu.curso

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProdutoLista( vModel : ProdutoViewModel,
                  paddingValues : PaddingValues = PaddingValues( 0.dp )) {
    Column ( modifier = Modifier.padding( paddingValues )) {
        Text("Lista de Produtos")
        LazyColumn {
            items( vModel.lista ) { produto ->
                val textColor = if (produto.ativo) Color.Black else Color.LightGray
                Card(
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    border = CardDefaults.outlinedCardBorder(true)
                ) {
                    Column(modifier = Modifier.padding(5.dp)) {
                        Row {
                            Column (Modifier.fillMaxWidth(0.7f)){
                                Text("Id: ${produto.id}", color = textColor, fontSize = 12.sp)
                                Text(produto.categoria, color = textColor, fontSize = 20.sp)
                                Text(produto.nome, color = textColor, fontSize = 18.sp)
                                Text("Minimo: ${produto.estoqueMinimo}", color = textColor)
                            }
                            Row(Modifier.fillMaxWidth(0.3f)) {
                                IconButton(onClick = { vModel.apagar( produto.id ) }) {
                                    Icon(Icons.Filled.Delete, contentDescription = "Apagar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


//@Composable
//fun ProdutoLista( vModel : ProdutoViewModel,
//                  paddingValues : PaddingValues = PaddingValues( 0.dp )) {
//    val scrollPosition = rememberScrollState(0)
//    Column ( modifier = Modifier.padding( paddingValues )
//        .verticalScroll(scrollPosition)) {
//        Text("Lista de Produtos")
//
//        for ( produto in vModel.lista) {
//            val textColor = if (produto.ativo) Color.Black else Color.LightGray
//            Card( modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
//                .fillMaxWidth(),
//                shape = RoundedCornerShape(10.dp),
//                elevation = CardDefaults.cardElevation(5.dp),
//                border = CardDefaults.outlinedCardBorder(true)
//            ) {
//                Column(modifier = Modifier.padding(5.dp)) {
//                    Text("${produto.categoria}", color = textColor, fontSize = 20.sp)
//                    Text("${produto.nome}", color = textColor, fontSize = 18.sp)
//                    Text("Minimo: ${produto.estoqueMinimo}", color = textColor)
//                }
//            }
//        }
//    }
//}