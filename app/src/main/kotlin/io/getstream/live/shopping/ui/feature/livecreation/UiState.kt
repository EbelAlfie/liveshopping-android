package io.getstream.live.shopping.ui.feature.livecreation

sealed interface CreationUiState {
  object Internal: CreationUiState

  object External: CreationUiState
}