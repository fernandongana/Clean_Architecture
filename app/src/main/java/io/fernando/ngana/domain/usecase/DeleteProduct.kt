package io.fernando.ngana.domain.usecase

import io.fernando.ngana.data.ProductsRepository

class DeleteProduct(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(productId: String) = productsRepository.deleteProduct(productId)
}
