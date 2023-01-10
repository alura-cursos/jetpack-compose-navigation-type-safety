package br.com.alura.panucci.ui.uistate

import br.com.alura.panucci.model.Product

data class HighlightsListUiState(
    val products: List<Product> = emptyList()
)