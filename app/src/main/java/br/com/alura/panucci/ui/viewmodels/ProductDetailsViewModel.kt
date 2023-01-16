package br.com.alura.panucci.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.alura.panucci.dao.ProductDao
import br.com.alura.panucci.ui.uistate.ProductDetailsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.math.BigDecimal
import kotlin.math.log
import kotlin.random.Random

class ProductDetailsViewModel(
    private val dao: ProductDao = ProductDao(),
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailsUiState>(
        ProductDetailsUiState.Loading
    )
    val uiState = _uiState.asStateFlow()
    private val discount = MutableStateFlow<BigDecimal>(BigDecimal.ZERO)

    init {
        viewModelScope.launch {
            savedStateHandle.getStateFlow<String?>(
                "productId",
                null
            ).filterNotNull()
                .collect(::findProductById)
        }
        viewModelScope.launch {
            discount.collect { currentDiscount ->
                if (currentDiscount > BigDecimal.ZERO) {
                    val currentState = _uiState.value
                    if (currentState is ProductDetailsUiState.Success) {
                        val product = currentState.product
                        val price = product.price
                        _uiState.value = currentState.copy(
                            product = product.copy(
                                price = price - (price * currentDiscount)
                            )
                        )
                    }
                }
            }
        }
        viewModelScope.launch {
            val timemillis = Random.nextLong(3000, 4000)
            delay(timemillis)
            savedStateHandle.getStateFlow<String?>(
                "promoCode",
                null
            ).filterNotNull()
                .collect(::applyDiscountCode)
        }
    }

    private fun applyDiscountCode(code: String) {
        when (code) {
            "PANUCCI10" -> discount.value = BigDecimal("0.1")
            else -> discount.value = BigDecimal.ZERO
        }
    }

    fun findProductById(id: String) {
        _uiState.update { ProductDetailsUiState.Loading }
        viewModelScope.launch {
            val timemillis = Random.nextLong(500, 2000)
            delay(timemillis)
            val dataState = dao.findById(id)?.let { product ->
                ProductDetailsUiState.Success(
                    product = product.copy(
                        price = product.price -
                                (product.price * discount.value)
                    )
                )
            } ?: ProductDetailsUiState.Failure
            _uiState.update { dataState }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val dao = ProductDao()
                ProductDetailsViewModel(
                    dao = dao,
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }

}