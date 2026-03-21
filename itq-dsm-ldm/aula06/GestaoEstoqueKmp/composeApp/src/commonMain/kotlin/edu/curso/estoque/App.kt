package edu.curso.estoque

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.ClearAll
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import edu.curso.estoque.theme.AppTheme


@OptIn(ExperimentalMaterial3ExpressiveApi::class,
        ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val vm = viewModel { GestaoEstoqueViewModel() }
    LaunchedEffect(vm.viewModelScope ) {
        vm.carregarTodos()
    }
    AppTheme(darkTheme = false) {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Gestão de Produto") },
                        actions = { IconButton(onClick = { vm.limparCampos() } ) {
                            Icon(   imageVector = Icons.Outlined.ClearAll,
                                    contentDescription = "Limpar Campos"  )}
                        })
                     },
            bottomBar = { BottomAppBar {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon( imageVector = Icons.Outlined.Edit,
                            contentDescription = "Formulario"  )},
                        selected = vm.backstack.value.last() == Rota.Formulario,
                        onClick = {
                            vm.backstack.value.clear()
                            vm.backstack.value.add( Rota.Formulario )
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon( imageVector = Icons.AutoMirrored.Outlined.List,
                            contentDescription = "Listagem"  )},
                        selected = vm.backstack.value.last() == Rota.Lista,
                        onClick = {
                            vm.backstack.value.clear()
                            vm.backstack.value.add( Rota.Lista )
                        }
                    )
                }
            } }
        ) { paddingValues ->
            // EstoqueFormulario( vm, paddingValues )
            NavDisplay(
                backStack = vm.backstack.value,
                entryProvider = entryProvider<NavKey> {
                    entry(Rota.Lista) {
                        EstoqueLista(vm, paddingValues)
                    }
                    entry(Rota.Formulario) {
                        EstoqueFormulario(vm, paddingValues)
                    }
                }
            )
        }
    }
}

@Composable
@Preview
fun AppPreview() {
    App()
}