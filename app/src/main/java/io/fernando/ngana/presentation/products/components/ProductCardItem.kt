package io.fernando.ngana.presentation.products.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.fernando.ngana.domain.model.Product

@Composable
fun ProductCardItem(
    product: Product,
    deleteProduct: (productId: String) -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp,
            )
            .fillMaxWidth(),
        elevation = 3.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = product.name.orEmpty(),
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                )
                Text(
                    text = product.category.orEmpty(),
                    color = Color.Gray,
                    fontSize = 13.sp,
                )
                Text(
                    text = product.description.orEmpty(),
                    color = Color.LightGray,
                    fontSize = 12.sp,
                )
            }
            Spacer(
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = { deleteProduct.invoke(product.id.orEmpty()) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    tint = Color.Green,
                    contentDescription = null,
                )
            }
        }
    }
}
