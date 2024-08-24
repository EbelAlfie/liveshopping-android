package io.getstream.live.shopping.ui.feature.livecreation

import io.getstream.live.shopping.ui.feature.model.BroadcastData
import io.getstream.video.android.core.Call

sealed interface StreamModeUiState {
  data class Internal(
    val call: Call,
  ) : StreamModeUiState

  data class External(
    val call: Call
  ) : StreamModeUiState

  data object Loading: StreamModeUiState

  data class Error(val error: Throwable): StreamModeUiState

  fun getIndex(): Int = when (this) {
    is External -> 1
    else -> 0
  }

}

enum class StreamChooseEvent {
  Internal,
  External
}