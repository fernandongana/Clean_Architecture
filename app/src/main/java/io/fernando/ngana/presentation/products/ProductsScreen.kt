package io.fernando.ngana.presentation.products

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import io.fernando.ngana.core.components.TopBar
import io.fernando.ngana.domain.model.Product
import io.fernando.ngana.presentation.products.components.DeleteProduct
import io.fernando.ngana.presentation.products.components.ProductCardItem
import io.fernando.ngana.presentation.products.components.Products

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = { TopBar() },
        content = { padding ->
            Products(
                productsContent = { products ->
                    ProductsContent(
                        padding = padding,
                        products = products,
                        deleteProduct = { productId ->
                            viewModel.deleteProduct(productId)
                        },
                    )
                },
            )
        },
    )
    DeleteProduct()
}

@Composable
fun ProductsContent(
    padding: PaddingValues,
    products: List<Product>,
    deleteProduct: (productId: String) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
        items(items = products) { product ->
            ProductCardItem(
                product = product,
                deleteProduct = { productId ->
                    deleteProduct(productId)
                },
            )
        }
    }
}
