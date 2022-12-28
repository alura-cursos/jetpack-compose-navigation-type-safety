package br.com.alura.panucci.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.ProductDetailsScreen
import br.com.alura.panucci.ui.viewmodels.ProductDetailsViewModel

private const val productDetailsRoute = "productDetails"
private const val productIdArgument = "productId"

fun NavGraphBuilder.productDetailsScreen(navController: NavHostController) {
    composable(
        "$productDetailsRoute/{$productIdArgument}"
    ) { backStackEntry ->
        backStackEntry.arguments?.getString(productIdArgument)?.let { id ->
            val viewModel = viewModel<ProductDetailsViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            LaunchedEffect(Unit) {
                viewModel.findProductById(id)
            }
            ProductDetailsScreen(
                uiState = uiState,
                onNavigateToCheckout = {
                    navController.navigateToCheckout()
                },
            )
        } ?: LaunchedEffect(Unit) {
            navController.navigateUp()
        }
    }
}

fun NavController.navigateToProductDetails(id: String){
    navigate("$productDetailsRoute/$id")
}