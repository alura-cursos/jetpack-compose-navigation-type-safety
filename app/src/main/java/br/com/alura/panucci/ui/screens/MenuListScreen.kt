package br.com.alura.panucci.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.panucci.model.Product
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.components.MenuProductCard
import br.com.alura.panucci.ui.theme.PanucciTheme
import br.com.alura.panucci.ui.theme.caveatFont
import br.com.alura.panucci.ui.uistate.MenuListUiState

@Composable
fun MenuListScreen(
    modifier: Modifier = Modifier,
    title: String = "Menu",
    onNavigateToDetails: (Product) -> Unit = {},
    uiState: MenuListUiState = MenuListUiState()
) {
    val products = uiState.products
    Column(
        modifier.fillMaxSize()
    ) {
        Surface {
            Text(
                text = title,
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                fontFamily = caveatFont,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { p ->
                MenuProductCard(
                    product = p,
                    Modifier
                        .clickable {
                            onNavigateToDetails(p)
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuListScreenPreview() {
    PanucciTheme {
        Surface {
            MenuListScreen(
                uiState = MenuListUiState(
                    products = sampleProducts
                )
            )
        }
    }
}