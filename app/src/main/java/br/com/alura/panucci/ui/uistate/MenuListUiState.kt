package br.com.alura.panucci.ui.uistate

import br.com.alura.panucci.model.Product

data class MenuListUiState(
    val products: List<Product> = emptyList()
)