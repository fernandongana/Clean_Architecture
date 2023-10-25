package io.fernando.ngana.domain.usecase

import io.fernando.ngana.data.ProductsRepository

class GetProducts(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke() = productsRepository.getProducts()
}
