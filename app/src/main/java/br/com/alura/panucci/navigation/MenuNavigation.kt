package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.MenuListScreen
import br.com.alura.panucci.ui.viewmodels.MenuListViewModel

internal const val menuRoute = "menu"

fun NavGraphBuilder.menuScreen(navController: NavHostController) {
    composable(menuRoute) {
        val viewModel = viewModel<MenuListViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        MenuListScreen(
            uiState = uiState,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            },
        )
    }
}

fun NavController.navigateToMenu(
    navOptions: NavOptions? = null
){
    navigate(menuRoute, navOptions)
}