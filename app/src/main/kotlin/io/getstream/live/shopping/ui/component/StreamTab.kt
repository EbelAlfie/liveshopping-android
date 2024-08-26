package io.getstream.live.shopping.ui.component

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.getstream.live.shopping.ui.feature.livecreation.StreamMode
import io.getstream.live.shopping.ui.feature.livecreation.CreationUiState

@Composable
fun StreamTab(
  creationUiState: CreationUiState,
  onTabSelected: (StreamMode) -> Unit
) {
  val tabIndex = creationUiState.getTabIndex()
  TabRow(selectedTabIndex = tabIndex) {
    Tab(
      selected = tabIndex == StreamMode.Internal.ordinal,
      onClick = { onTabSelected.invoke(StreamMode.Internal) },
      text = {
        Text(text = "Device")
      }
    )
    Tab(
      selected = tabIndex == StreamMode.External.ordinal,
      onClick = {
        onTabSelected.invoke(StreamMode.External)
      },
      text = {
        Text(text = "OBS")
      }
    )
  }
}