package edu.curso.estoque

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.ClearAll
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FmdGood
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import edu.curso.estoque.theme.AppTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3ExpressiveApi::class,
        ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val vmPrincipal = viewModel{ PrincipalViewModel()  }
    val vm = viewModel { GestaoEstoqueViewModel() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(vm.viewModelScope ) {
        vm.carregarTodos()
    }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val screeModeIcon = if (vm.darkMode) Icons.Outlined.LightMode else Icons.Outlined.DarkMode
    AppTheme(darkTheme = vm.darkMode) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Column {
                        Text( "Navegação Principal" )
                        Spacer(modifier = Modifier.height( 8.dp ))
                        Row( modifier = Modifier.fillMaxWidth().clickable{
                            vmPrincipal.navigateTo(RotaPrincipal.Estoque)
                            scope.launch {
                                drawerState.close()
                            }
                        }) {
                            Icon( Icons.Outlined.FmdGood, contentDescription = "Estoque" )
                            Text( "Estoque" )
                        }
                        Row( modifier = Modifier.fillMaxWidth().clickable{
                            vmPrincipal.navigateTo(RotaPrincipal.Cliente)
                            scope.launch {
                                drawerState.close()
                            }
                        }) {
                            Icon( Icons.Outlined.VerifiedUser, contentDescription = "Cliente" )
                            Text( "Cliente" )
                        }
                    }
                }
            }
        ) {
            Scaffold(
                topBar = { TopAppBar(title = { Text("Gestão de Produto") },
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                } ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Menu,
                                        contentDescription = "Screen Mode"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { vm.limparCampos() } ) {
                                    Icon(   imageVector = Icons.Outlined.ClearAll,
                                        contentDescription = "Limpar Campos"  )
                                }
                                IconButton(onClick = { vm.toggleScreenMode() } ) {
                                    Icon(   imageVector = screeModeIcon,
                                        contentDescription = "Screen Mode"  )
                                }
                            })
                         },
                bottomBar = { BottomAppBar {
                    NavigationBar {
                        NavigationBarItem(
                            icon = { Icon( imageVector = Icons.Outlined.Edit,
                                contentDescription = "Formulario"  )},
                            selected = vm.backstack.value.last() == RotaEstoque.Formulario,
                            onClick = {
                                vm.navigateTo( RotaEstoque.Formulario)
                            }
                        )
                        NavigationBarItem(
                            icon = { Icon( imageVector = Icons.AutoMirrored.Outlined.List,
                                contentDescription = "Listagem"  )},
                            selected = vm.backstack.value.last() == RotaEstoque.Lista,
                            onClick = {
                                vm.navigateTo( RotaEstoque.Lista )
                            }
                        )
                    }
                } },
                snackbarHost = { SnackbarHost( vm.snackMessage )},

            ) { paddingValues ->
                NavDisplay(
                    backStack = vmPrincipal.backstack.value,
                    entryProvider = entryProvider<NavKey> {
                        entry(RotaPrincipal.Estoque) {
                            EstoqueScreen( vm, paddingValues )
                        }
                        entry(RotaPrincipal.Cliente) {
                            ClienteScreen( paddingValues )
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun AppPreview() {
    App()
}