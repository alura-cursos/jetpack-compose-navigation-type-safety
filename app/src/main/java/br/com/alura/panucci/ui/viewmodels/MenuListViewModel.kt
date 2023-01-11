package br.com.alura.panucci.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.panucci.dao.ProductDao
import br.com.alura.panucci.ui.uistate.MenuListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuListViewModel(
    private val dao: ProductDao = ProductDao()
) : ViewModel() {

    private val _uiState = MutableStateFlow(MenuListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            dao.products.collect { products ->
                _uiState.update {
                    it.copy(products = products)
                }
            }
        }
    }

}