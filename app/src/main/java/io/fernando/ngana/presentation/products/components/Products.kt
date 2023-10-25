package io.fernando.ngana.presentation.products.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import io.fernando.ngana.core.ApiResponse
import io.fernando.ngana.core.components.ProgressBar
import io.fernando.ngana.domain.model.Product
import io.fernando.ngana.presentation.products.ProductsViewModel

@Composable
fun Products(
    viewModel: ProductsViewModel = hiltViewModel(),
    productsContent: @Composable (productList: List<Product>) -> Unit,
) {
    when (val productsResponse = viewModel.productsResponse) {
        is ApiResponse.Loading -> ProgressBar()
        is ApiResponse.Success -> productsContent(productsResponse.data)
        is ApiResponse.Failure -> print(productsResponse.e)
    }
}
