package br.com.alura.panucci.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.panucci.R
import br.com.alura.panucci.model.Product
import br.com.alura.panucci.sampledata.sampleProductWithImage
import br.com.alura.panucci.sampledata.sampleProductWithoutImage
import br.com.alura.panucci.ui.theme.PanucciTheme
import coil.compose.AsyncImage

@Composable
fun HighlightProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onOrderClick: () -> Unit = {}
) {
    Card(
        modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
    ) {
        Column(Modifier.fillMaxWidth()) {
            product.image?.let { image ->
                AsyncImage(
                    image,
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth()
                        .height(116.dp),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
            ) {
                Text(text = product.name)
                Text(text = product.price.toString())
                Spacer(Modifier.height(16.dp))
                Text(
                    text = product.description,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(18.dp))
            Button(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    )
                    .clickable {
                        onOrderClick()
                    }
                    .align(Alignment.End),
                onClick = {
                    onOrderClick()
                }
            ) {
                Text(
                    text = "Pedir",
                )
            }
        }
    }
}

@Preview
@Composable
private fun HighlightProductPreview() {
    PanucciTheme {
        HighlightProductCard(
            product = sampleProductWithoutImage
        )
    }
}

@Preview
@Composable
private fun HighlightProductCardWithImagePreview() {
    PanucciTheme {
        HighlightProductCard(
            product = sampleProductWithImage
        )
    }
}