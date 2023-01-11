package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navOptions
import androidx.navigation.navigation
import br.com.alura.panucci.model.Product
import br.com.alura.panucci.ui.components.BottomAppBarItem

internal const val homeGraphRoute = "home"

fun NavGraphBuilder.homeGraph(
    onNavigateToCheckout: () -> Unit,
    onNavigateToProductDetails: (Product) -> Unit
) {
    navigation(
        startDestination = highlightsListRoute,
        route = homeGraphRoute
    ) {
        highlightsListScreen(
            onNavigateToCheckout,
            onNavigateToProductDetails
        )
        menuScreen(
            onNavigateToProductDetails
        )
        drinksScreen(
            onNavigateToProductDetails
        )
    }
}

fun NavController.navigateToHomeGraph() {
    navigate(homeGraphRoute)
}

fun NavController.navigateSingleTopWithPopUpTo(
    item: BottomAppBarItem
) {
    val (route, navigate) = when (item) {
        BottomAppBarItem.Drinks -> Pair(
            drinksRoute,
            ::navigateToDrinks
        )
        BottomAppBarItem.HighlightsList -> Pair(
            highlightsListRoute,
            ::navigateToHighlightsList
        )
        BottomAppBarItem.Menu -> Pair(
            menuRoute,
            ::navigateToMenu
        )
    }

    val navOptions = navOptions {
        launchSingleTop = true
        popUpTo(route)
    }
    navigate(navOptions)
}