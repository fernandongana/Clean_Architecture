package io.fernando.ngana.data

import com.google.firebase.firestore.CollectionReference
import io.fernando.ngana.core.ApiResponse
import io.fernando.ngana.core.Constants.NAME
import io.fernando.ngana.domain.model.Product
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepositoryImpl @Inject constructor(
    private val productRef: CollectionReference,
) : ProductsRepository {

    override suspend fun getProducts() = callbackFlow {
        val snapshotListener = productRef.orderBy(NAME).addSnapshotListener { snapshot, e ->
            val productResponse = if (snapshot != null) {
                val products = snapshot.toObjects(Product::class.java)
                ApiResponse.Success(products)
            } else {
                ApiResponse.Failure(e)
            }
            trySend(productResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addProduct(name: String, category: String, description: String) = try {
        val id = productRef.document().id
        val product = Product(
            id = id,
            name = name,
            category = category,
            description = description,
        )
        productRef.document(id).set(product).await()
        ApiResponse.Success(true)
    } catch (e: Exception) {
        ApiResponse.Failure(e)
    }

    override suspend fun deleteProduct(productId: String) = try {
        productRef.document(productId).delete().await()
        ApiResponse.Success(true)
    } catch (e: Exception) {
        ApiResponse.Failure(e)
    }
}
