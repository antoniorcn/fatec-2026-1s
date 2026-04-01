package edu.curso

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ProdutoForm(vModel : ProdutoViewModel,
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
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Id:") }, enabled = false,
            value = vModel.id ?: "", onValueChange = {},
            suffix = {
                IconButton(onClick = {
                    vModel.id = null
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Novo")
                }
            })
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Categoria:") }, placeholder = { Text("Categoria do produto") },
            value = vModel.categoria, onValueChange = { texto ->
                vModel.categoria = texto
                println("Categoria: $vModel.categoria")

            })
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Sku:") }, placeholder = { Text("Código interno do produto") },
            value = vModel.sku, onValueChange = { vModel.sku = it })
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome:") }, placeholder = { Text("Nome do produto") },
            value = vModel.nome, onValueChange = { vModel.nome = it })
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Descrição:") }, placeholder = { Text("Descrição do produto") },
            value = vModel.descricao, onValueChange = { vModel.descricao = it })
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Estoque Minimo:") },
            placeholder = { Text("Quantidade minima de estoque permitido para o produto") },
            value = vModel.estoqueMinimo,
            onValueChange = { vModel.estoqueMinimo = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Produto está ativo ?")
            Switch(checked = vModel.ativo, onCheckedChange = { vModel.ativo = it })
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Criado em:") }, enabled = false,
            value = vModel.criadoEm, onValueChange = { vModel.criadoEm = it })
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Imagem URL:") }, placeholder = { Text("Imagem do produto") },
            value = vModel.imagemUrl, onValueChange = { vModel.imagemUrl = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                vModel.salvar()
                vModel.limparCampos()
            }) {
                Text("Salvar")
            }
            Button(onClick = {
                vModel.pesquisar()
            }) {
                Text("Pesquisar")
            }
        }
    }
}