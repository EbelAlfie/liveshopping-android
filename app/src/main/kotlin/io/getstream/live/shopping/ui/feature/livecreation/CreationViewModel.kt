package io.getstream.live.shopping.ui.feature.livecreation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.live.shopping.chat.createStreamerChannel
import io.getstream.video.android.core.StreamVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreationViewModel @Inject constructor() : ViewModel() {

  private val _uiState = MutableStateFlow<StreamModeUiState>(StreamModeUiState.Loading)
  val uiState = _uiState.asStateFlow()

  suspend fun createCall(type: String) {
    setState { StreamModeUiState.Loading }

    val channel = ChatClient
      .instance()
      .createStreamerChannel()

    if (channel == null) {
      setState { StreamModeUiState.Error(Throwable()) }
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

    setState {
      StreamModeUiState.Internal(call = call)
    }
  }

  fun setBroadcastMode(event: StreamChooseEvent) {
    setState {
      when (event) {
        StreamChooseEvent.Internal ->
          (this as? StreamModeUiState.External)?.let {
            StreamModeUiState.Internal(call = it.call)
          } ?: this

        StreamChooseEvent.External ->
          (this as? StreamModeUiState.Internal)?.let {
            StreamModeUiState.External(call = it.call)
          } ?: this
      }
    }
  }

  private fun setState(reducer: StreamModeUiState.() -> StreamModeUiState) {
    _uiState.value = _uiState.value.reducer()
  }

}
