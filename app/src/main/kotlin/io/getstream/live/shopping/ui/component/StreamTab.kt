package io.getstream.live.shopping.ui.component

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import io.getstream.live.shopping.ui.feature.livecreation.StreamChooseEvent
import io.getstream.live.shopping.ui.feature.livecreation.StreamModeUiState

@Composable
fun StreamTab(
  streamModeUiState: StreamModeUiState,
  onTabSelected: (StreamChooseEvent) -> Unit
) {
  TabRow(selectedTabIndex = streamModeUiState.getIndex()) {
    Tab(
      selected = streamModeUiState is StreamModeUiState.Internal,
      onClick = { onTabSelected.invoke(StreamChooseEvent.Internal) }
    )
    Tab(
      selected = streamModeUiState is StreamModeUiState.External,
      onClick = {
        onTabSelected.invoke(StreamChooseEvent.External)
      }
    )
  }
}