package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.CheckoutScreen

private const val checkoutRoute = "checkout"

fun NavGraphBuilder.checkoutScreen(navController: NavHostController) {
    composable(checkoutRoute) {
        CheckoutScreen(
            products = sampleProducts,
            onPopBackStack = {
                navController.navigateUp()
            },
        )
    }
}

fun NavController.navigateToCheckout(){
    navigate(checkoutRoute)
}