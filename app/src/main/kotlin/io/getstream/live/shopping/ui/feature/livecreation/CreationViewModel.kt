package io.getstream.live.shopping.ui.feature.livecreation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.live.shopping.ui.feature.liveshopping.LiveShoppingUiState
import io.getstream.video.android.core.StreamVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreationViewModel @Inject constructor(): ViewModel() {
  private val _uiState = MutableStateFlow<CreationUiState>(CreationUiState.Internal)
  val uiState = _uiState.asStateFlow()

  suspend fun createCall(type: String, id: String) {
    val streamVideo = StreamVideo.instance()
    val activeCall = streamVideo.state.activeCall.value
    val call = if (activeCall != null) {
      if (activeCall.id != id) {
        // If the call id is different leave the previous call
        activeCall.leave()
        // Return a new call
        streamVideo.call(type = type, id = id)
      } else {
        // Call ID is the same, use the active call
        activeCall
      }
    } else {
      // There is no active call, create new call
      streamVideo.call(type = type, id = id)
    }
    val result = call.join(create = true)
    result.onSuccess {
      subscribePinnedProduct(call)
      liveShoppingUiStateMutableState.value = LiveShoppingUiState.Success(call)
    }.onError {
      liveShoppingUiStateMutableState.value = LiveShoppingUiState.Error
    }
  }
}
