package br.com.alura.panucci.ui.uistate

import br.com.alura.panucci.model.Product

data class DrinksListUiState(
    val products: List<Product> = emptyList()
)