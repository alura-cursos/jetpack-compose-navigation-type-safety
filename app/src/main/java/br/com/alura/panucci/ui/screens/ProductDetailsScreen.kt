package br.com.alura.panucci.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.panucci.R
import br.com.alura.panucci.model.Product
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.theme.PanucciTheme
import br.com.alura.panucci.ui.uistate.ProductDetailsUiState
import coil.compose.AsyncImage

@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    onNavigateToCheckout: () -> Unit = {},
    uiState: ProductDetailsUiState = ProductDetailsUiState()
) {
    uiState.product?.let { product ->
        Column(
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            product.image?.let {
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(product.name, fontSize = 24.sp)
                Text(product.price.toPlainString(), fontSize = 18.sp)
                Text(product.description)
                Button(
                    onClick = { onNavigateToCheckout() },
                    Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Pedir")
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductDetailsScreenPreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                uiState = ProductDetailsUiState(
                    product = sampleProducts.random()
                ),
            )
        }
    }
}