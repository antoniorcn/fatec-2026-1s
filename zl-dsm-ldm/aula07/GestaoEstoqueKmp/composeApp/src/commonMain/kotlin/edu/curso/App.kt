@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package edu.curso

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import edu.curso.theme.AppTheme
import kotlinx.coroutines.launch


@Preview(backgroundColor = 0xffffffff, showSystemUi = true)
@Composable
fun App() {
    val scope = rememberCoroutineScope()
    val produtoVM = viewModel { ProdutoViewModel() }

    LaunchedEffect(produtoVM.viewModelScope) {
        produtoVM.carregarTodos()
    }

    AppTheme(darkTheme = produtoVM.isDarkTheme) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Text("Módulos do Sistema")
                    Row {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Estoque")
                        Text("Estoque")
                    }
                    Row {
                        Icon(Icons.Filled.Contacts, contentDescription = "Contatos")
                        Text("Contatos")
                    }
                }
            },
            drawerState =produtoVM.drawerState,
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                "Gestão de Produtos", fontSize = 32.sp
                            )
                        },
                        actions = {
                            IconButton(onClick = { produtoVM.limparCampos() }) {
                                Icon(Icons.Filled.FormatPaint, contentDescription = "Limpar Campos")
                            }
                            IconButton(onClick = {
                                produtoVM.toggleDarkTheme()
                            }) {
                                Icon(produtoVM.iconeThema(), contentDescription = "Mode Theme")
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    produtoVM.drawerState.open()
                                }
                            }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        },
                        modifier = Modifier
                    )
                },
                bottomBar = {
                    BottomAppBar {
                        NavigationBar {
                            NavigationBarItem(
                                selected = produtoVM.backstack.last() is Rotas.Listagem,
                                onClick = { produtoVM.navigateTo(Rotas.Listagem) },
                                icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Listagem") }
                            )
                            NavigationBarItem(
                                selected = produtoVM.backstack.last() is Rotas.Formulario,
                                onClick = { produtoVM.navigateTo(Rotas.Formulario) },
                                icon = { Icon(Icons.Filled.Edit, contentDescription = "Formulario") }
                            )
                        }
                        Text(
                            "Fatec Zona Leste - D.S.M.", fontSize = 22.sp
                        )
                    }
                },
                snackbarHost = { SnackbarHost(produtoVM.snack) }
            ) { paddingValues ->
                NavDisplay(
                    backStack = produtoVM.backstack,
                    entryProvider = entryProvider {
                        entry(Rotas.Listagem) { _ ->
                            ProdutoLista(produtoVM, paddingValues = paddingValues)
                        }
                        entry(Rotas.Formulario) { _ ->
                            ProdutoForm(produtoVM, paddingValues = paddingValues)
                        }
                    }
                )

            }
        }
    }
}