package io.getstream.live.shopping.ui.feature.livecreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.getstream.live.shopping.R
import io.getstream.live.shopping.ui.component.StreamButton
import io.getstream.live.shopping.ui.component.StreamTab
import io.getstream.live.shopping.ui.component.UrlText
import io.getstream.live.shopping.ui.feature.livecreation.StreamMode.External
import io.getstream.live.shopping.ui.navigation.LiveShoppingScreen
import io.getstream.live.shopping.ui.navigation.currentComposeNavigator

@Composable
fun ChannelCreationScreen(
  viewModel: CreationViewModel = hiltViewModel()
) {
  val navigator = currentComposeNavigator

  val uiState by viewModel.uiState.collectAsState()

  Column (
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = stringResource(id = R.string.stream_creation_title),
      fontWeight = FontWeight.Bold,
      fontSize = 30.sp,
      color = Color.Black
    )

    StreamTab(uiState, viewModel::setBroadcastMode)

    UrlText(
      modifier = Modifier.weight(1F),
      url = (uiState as? CreationUiState.Success)?.let {
        if (it.streamMode == External) it.call.state.ingress.value?.rtmp?.address else null
      } ?: ""
    )

    UrlText(
      modifier = Modifier.weight(1F),
      url = (uiState as? CreationUiState.Success)?.let {
        if (it.streamMode == External) it.call.state.ingress.value?.rtmp?.streamKey else null
      } ?: ""
    )

    StreamButton(
      text = stringResource(id = R.string.livestream_to_backstage),
      enabled = uiState is CreationUiState.Success
    ) {
      (uiState as? CreationUiState.Success)?.let {
        navigator.navigate(
          LiveShoppingScreen.LiveShopping(cid = it.call.cid, isHost = true)
        )
      }
    }
  }

}