package io.fernando.ngana.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fernando.ngana.core.Constants.PRODUCTS
import io.fernando.ngana.data.ProductsRepository
import io.fernando.ngana.data.ProductsRepositoryImpl
import io.fernando.ngana.domain.usecase.AddProduct
import io.fernando.ngana.domain.usecase.DeleteProduct
import io.fernando.ngana.domain.usecase.GetProducts
import io.fernando.ngana.domain.usecase.UseCases

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideProductsRef() = Firebase.firestore.collection(PRODUCTS)

    @Provides
    fun provideProductsRepository(
        productsRef: CollectionReference,
    ): ProductsRepository = ProductsRepositoryImpl(productsRef)

    @Provides
    fun provideUseCases(productsRepository: ProductsRepository) = UseCases(
        getProducts = GetProducts(productsRepository),
        addProduct = AddProduct(productsRepository),
        deleteProduct = DeleteProduct(productsRepository),
    )
}
