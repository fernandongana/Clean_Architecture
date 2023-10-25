package io.fernando.ngana.core

sealed class ApiResponse<out T> {
    data object Loading : ApiResponse<Nothing>()

    data class Success<out T>(
        val data: T,
    ) : ApiResponse<T>()

    data class Failure(
        val e: Exception?,
    ) : ApiResponse<Nothing>()
}
