package br.com.alura.panucci.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.panucci.model.Product
import br.com.alura.panucci.sampledata.sampleProductWithImage
import br.com.alura.panucci.sampledata.sampleProductWithoutImage
import br.com.alura.panucci.ui.theme.PanucciTheme
import coil.compose.AsyncImage

@Composable
fun DrinkProductCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .width(158.dp)
            .heightIn(
                min = 100.dp
            ),
        shape = RoundedCornerShape(12.dp)
    ) {
        product.image?.let { image ->
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = product.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = product.price.toPlainString(), maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun DrinkProductCardPreview() {
    PanucciTheme {
        DrinkProductCard(
            product = sampleProductWithoutImage
        )
    }
}

@Preview
@Composable
private fun DrinkProductCardWithImagePreview() {
    PanucciTheme {
        DrinkProductCard(
            product = sampleProductWithImage
        )
    }
}