package io.getstream.live.shopping.ui.feature.liveshopping

import io.getstream.live.shopping.ProductModel
import io.getstream.video.android.core.Call

sealed interface LiveShoppingUiState {

  data object Loading : LiveShoppingUiState

  data class Success(
    val isHost: Boolean,
    val call: Call
  ) : LiveShoppingUiState

  data object Error : LiveShoppingUiState
}

sealed interface ProductBannerUiState {
  data object Idle: ProductBannerUiState

  data class Pinned(
    val productData: ProductModel
  ): ProductBannerUiState
}