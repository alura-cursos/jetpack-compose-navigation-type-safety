package br.com.alura.panucci.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.panucci.R
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.theme.PanucciTheme
import br.com.alura.panucci.ui.uistate.ProductDetailsUiState
import coil.compose.AsyncImage

@Composable
fun ProductDetailsScreen(
    uiState: ProductDetailsUiState,
    modifier: Modifier = Modifier,
    onOrderClick: () -> Unit = {},
    onTryFindProductAgainClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    when (uiState) {
        ProductDetailsUiState.Failure -> {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Falha ao buscar o produto")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onTryFindProductAgainClick) {
                    Text(text = "Tentar buscar novamente")
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = onBackClick) {
                    Text(text = "Voltar")
                }
            }
        }
        ProductDetailsUiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
        is ProductDetailsUiState.Success -> {
            val product = uiState.product
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
                        onClick = { onOrderClick() },
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
}

@Preview
@Composable
fun ProductDetailsScreenWithSuccessStatePreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                uiState = ProductDetailsUiState.Success(
                    sampleProducts.random()
                ),
            )
        }
    }
}

@Preview
@Composable
fun ProductDetailsScreenWithFailureStatePreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                uiState = ProductDetailsUiState.Failure,
            )
        }
    }
}

@Preview
@Composable
fun ProductDetailsScreenWithLoadingStatePreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                uiState = ProductDetailsUiState.Loading,
            )
        }
    }
}