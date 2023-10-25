package io.fernando.ngana.domain.usecase

import io.fernando.ngana.data.ProductsRepository

class AddProduct(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(
        name: String,
        category: String,
        description: String,
    ) = productsRepository.addProduct(name, category, description)
}
