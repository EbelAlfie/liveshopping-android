package io.getstream.live.shopping.ui.feature.livecreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.live.shopping.chat.createStreamerChannel
import io.getstream.result.extractCause
import io.getstream.video.android.core.CreateCallOptions
import io.getstream.video.android.core.StreamVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.openapitools.client.models.BroadcastSettingsRequest
import org.openapitools.client.models.CallSettingsRequest
import org.openapitools.client.models.VideoSettingsRequest
import javax.inject.Inject

@HiltViewModel
class CreationViewModel @Inject constructor() : ViewModel() {

  private val _uiState = MutableStateFlow<CreationUiState>(CreationUiState.Loading)
  val uiState = _uiState.asStateFlow()

  suspend fun createCall(type: String) {
    setState { CreationUiState.Loading }

    val channel = ChatClient
      .instance()
      .createStreamerChannel()

    if (channel == null) {
      setState { CreationUiState.Error(Throwable()) }
      return
    }

    val streamVideo = StreamVideo.instance()
    val activeCall = streamVideo.state.activeCall.value
    val call = if (activeCall != null) {
      if (activeCall.id != channel.id) {
        // If the call id is different leave the previous call
        activeCall.leave()
        // Return a new call
        streamVideo.call(type = type, id = channel.id)
      } else {
        // Call ID is the same, use the active call
        activeCall
      }
    } else {
      // There is no active call, create new call
      streamVideo.call(type = type, id = channel.id)
    }

    //call.camera.setEnabled(enabled = false, fromUser = true)

    call.join(create = true)
      .onSuccess {
        setState { CreationUiState.Success(call = call) }
      }
      .onError {
        setState { CreationUiState.Error(it.extractCause()) }
      }

  }

  fun setBroadcastMode(mode: StreamMode) {
    setState {
      (this as? CreationUiState.Success)?.let {
        it.call.camera.setEnabled(enabled = mode == StreamMode.Internal, fromUser = true)
        it.copy(streamMode = mode)
      } ?: this
    }
  }

  private fun setState(reducer: CreationUiState.() -> CreationUiState) {
    _uiState.value = _uiState.value.reducer()
  }

}
