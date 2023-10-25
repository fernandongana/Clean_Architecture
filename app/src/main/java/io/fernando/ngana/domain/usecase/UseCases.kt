package io.fernando.ngana.domain.usecase

data class UseCases(
    val getProducts: GetProducts,
    val addProduct: AddProduct,
    val deleteProduct: DeleteProduct,
)
