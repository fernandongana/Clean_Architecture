package io.fernando.ngana.presentation.products

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.fernando.ngana.core.ApiResponse
import io.fernando.ngana.domain.model.Product
import io.fernando.ngana.domain.usecase.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    var productsResponse by mutableStateOf<ApiResponse<List<Product>>>(ApiResponse.Loading)
        private set
    var addProductResponse by mutableStateOf<ApiResponse<Boolean>>(ApiResponse.Success(false))
        private set
    var deleteProductResponse by mutableStateOf<ApiResponse<Boolean>>(ApiResponse.Success(false))
        private set

    init {
        getProducts()
    }

    private fun getProducts() = viewModelScope.launch(Dispatchers.IO) {
        useCases.getProducts().collect { response ->
            Log.v("getProducts: ", "$response")
            productsResponse = response
        }
    }

    fun addProduct(name: String, category: String, description: String) =
        viewModelScope.launch(Dispatchers.IO) {
            addProductResponse = ApiResponse.Loading
            addProductResponse = useCases.addProduct(name, category, description)
        }

    fun deleteProduct(productId: String) = viewModelScope.launch(Dispatchers.IO) {
        deleteProductResponse = ApiResponse.Loading
        deleteProductResponse = useCases.deleteProduct(productId)
    }
}
