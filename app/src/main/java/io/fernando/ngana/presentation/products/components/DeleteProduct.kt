package io.fernando.ngana.presentation.products.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import io.fernando.ngana.core.ApiResponse
import io.fernando.ngana.core.components.ProgressBar
import io.fernando.ngana.presentation.products.ProductsViewModel

@Composable
fun DeleteProduct(viewModel: ProductsViewModel = hiltViewModel()) {
    when (val deleteProductResponse = viewModel.deleteProductResponse) {
        is ApiResponse.Loading -> ProgressBar()
        is ApiResponse.Success -> Unit
        is ApiResponse.Failure -> print(deleteProductResponse.e)
    }
}
