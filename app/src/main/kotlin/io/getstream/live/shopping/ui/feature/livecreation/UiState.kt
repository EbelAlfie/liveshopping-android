package io.getstream.live.shopping.ui.feature.livecreation

import io.getstream.video.android.core.Call

sealed interface CreationUiState {

  data class Success(
    val call: Call,
    val streamMode: StreamMode = StreamMode.Internal
  ) : CreationUiState

  data object Loading: CreationUiState

  data class Error(val error: Throwable): CreationUiState

  fun getTabIndex() =
    when (this) {
      is Success -> streamMode.ordinal
      else -> 0
    }
}

enum class StreamMode {
  Internal,
  External
}