package io.fernando.ngana.data

import io.fernando.ngana.core.ApiResponse
import io.fernando.ngana.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Flow<ApiResponse<List<Product>>>

    suspend fun addProduct(
        name: String,
        category: String,
        description: String,
    ): ApiResponse<Boolean>

    suspend fun deleteProduct(productId: String): ApiResponse<Boolean>
}
