package io.getstream.live.shopping.ui.component.products

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.getstream.live.shopping.ProductModel
import io.getstream.live.shopping.ui.component.PinButton

@Composable
fun ProductList(
  products: List<ProductModel>,
  isHost: Boolean,
  onItemSelected: (String) -> Unit
) {
  LazyColumn {
    items(products) {
      ProductBanner(
        image = it.image,
        name = it.name,
        description = it.desc,
        leadingContent = {
          if (isHost)
            PinButton(
              onClick = {
                onItemSelected.invoke(it.id)
              }
            )
        }
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
  onDismiss: () -> Unit,
  content: @Composable () -> Unit = {}
) {
  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = rememberModalBottomSheetState()
  ) {
    content()
  }

}